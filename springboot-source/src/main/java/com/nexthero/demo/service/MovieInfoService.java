package com.nexthero.demo.service;

import com.nexthero.demo.model.MovieCarousel;
import com.nexthero.demo.model.MovieInfo;
import com.nexthero.demo.repository.MovieCarouselRepository;
import com.nexthero.demo.repository.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
