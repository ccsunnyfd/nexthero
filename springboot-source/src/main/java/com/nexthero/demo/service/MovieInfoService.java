package com.nexthero.demo.service;

import com.google.common.collect.Lists;
import com.nexthero.demo.model.MovieCarousel;
import com.nexthero.demo.model.MovieInfo;
import com.nexthero.demo.repository.MovieCarouselRepository;
import com.nexthero.demo.repository.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * MovieInfoService
 *
 * @version 1.0
 */
@Service
public class MovieInfoService {
    private MovieInfoRepository movieInfoRepository;

    @Autowired
    public void setMovieInfoRepository(MovieInfoRepository movieInfoRepository) {
        this.movieInfoRepository = movieInfoRepository;
    }

    public List<MovieInfo> getMovieInfo() {
        return movieInfoRepository.findAll();
    }

    public String addNewMovieInfo(MovieInfo movieInfo) {
        MovieInfo newMovieInfo = movieInfoRepository.save(movieInfo);
        return newMovieInfo.getId();
    }


    // 注意，PageRequest.of的第一个参数表示第几页，从0开始计数，这与通常的分页从1开始有些不同，要处理下
    //动态构造查询语句
    public Page<MovieInfo> getPageMovies(String keywords, Integer pageNum, Integer
            size) {
        Specification querySpeci = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                if (!StringUtils.isEmpty(keywords)) {
                    Predicate p1 = criteriaBuilder.like(root.get("name"), "%" + keywords + "%");
                    Predicate p2 = criteriaBuilder.like(root.get("originalName"), "%" + keywords + "%");
                    predicate = criteriaBuilder.or(p1, p2);
                }

                return predicate;
            }
        };
        Page<MovieInfo> page = movieInfoRepository.findAll(querySpeci, PageRequest.of(pageNum - 1, size));
        return page;
    }

}
