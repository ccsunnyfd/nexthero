package com.nexthero.demo.controller;

import com.nexthero.demo.model.MovieInfo;
import com.nexthero.demo.model.RespBean;
import com.nexthero.demo.service.MovieInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MovieInfoController
 *
 * @version 1.0
 */
@RestController
@RequestMapping(value = "api/movieInfo")
@Api(value = "电影信息的api接口")
public class MovieInfoController {
    private MovieInfoService movieInfoService;

    @Autowired
    public void setMovieInfoService(MovieInfoService movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @PostMapping("list")
    @ApiOperation(value = "获取电影信息列表")
    public Map<String, Object> getMovieInfo() {
        Map<String, Object> map = new HashMap<>();
        List<MovieInfo> res = null;
        RespBean respBean = null;
        try {
            res = movieInfoService.getMovieInfo();
            respBean = new RespBean("success", "获取电影信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            respBean = new RespBean("failure", "获取电影信息失败");
        }

        map.put("status", respBean.getStatus());
        map.put("msg", respBean.getMsg());
        map.put("data", res);
        return map;
    }


    @GetMapping("search/list")
    @ApiOperation(value = "获取电影搜索信息分页")
    public Map<String, Object> getMovieByKeywords(@RequestParam(value = "keywords") String keywords,
                                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "9") Integer size) {
        Map<String, Object> map = new HashMap<>();
        List<MovieInfo> movies = null;
        RespBean respBean = null;
        int totalCount = 0;
        try {
            Page<MovieInfo> pageMovies = movieInfoService.getPageMovies(keywords == null ? null : keywords.trim(), page, size);
            movies = pageMovies.getContent();
            totalCount = (int) pageMovies.getTotalElements();
            respBean = new RespBean("success", "获取电影搜索信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            respBean = new RespBean("failure", "获取电影搜索信息失败");
        }

        map.put("status", respBean.getStatus());
        map.put("msg", respBean.getMsg());
        map.put("totalCount", totalCount);
        map.put("data", movies);
        return map;
    }


    @PostMapping("add")
    @ApiOperation(value = "添加新的电影信息")
    public RespBean addNewMovieInfo(@RequestBody MovieInfo movieInfo) {
        String newId = movieInfoService.addNewMovieInfo(movieInfo);
        if (newId != null) {
            return new RespBean("success", newId + "");
        } else {
            return new RespBean("error", "添加电影信息失败");
        }
    }
}
