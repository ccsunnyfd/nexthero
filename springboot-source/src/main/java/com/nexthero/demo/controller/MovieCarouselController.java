package com.nexthero.demo.controller;

import com.nexthero.demo.model.MovieCarousel;
import com.nexthero.demo.model.RespBean;
import com.nexthero.demo.service.MovieCarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MovieCarouselController
 *
 * @version 1.0
 */
@RestController
@RequestMapping(value = "api/movieCarousel")
@Api(value = "电影轮播图的api接口")
public class MovieCarouselController {
    private MovieCarouselService movieCarouselService;

    @Autowired
    public void setMovieCarouselService(MovieCarouselService movieCarouselService) {
        this.movieCarouselService = movieCarouselService;
    }

    @PostMapping("list")
    @ApiOperation(value = "获取所有轮播图信息列表")
    public Map<String, Object> getMovieCarousel() {
        Map<String, Object> map = new HashMap<>();
        List<MovieCarousel> res = null;
        RespBean respBean = null;
        try {
            res = movieCarouselService.getMovieCarousel();
            respBean = new RespBean("success", "获取轮播图信息列表成功");
        } catch (Exception e) {
            e.printStackTrace();
            respBean = new RespBean("failure", "获取轮播图信息列表失败");
        }

        map.put("status", respBean.getStatus());
        map.put("msg", respBean.getMsg());
        map.put("data", res);
        return map;
    }

    @PostMapping("add")
    @ApiOperation(value = "添加新的轮播图信息")
    public RespBean addNewCarousel(@RequestBody MovieCarousel movieCarousel) {
        Long newId = movieCarouselService.addNewCarousel(movieCarousel);
        if (newId != null) {
            return new RespBean("success", newId + "");
        } else {
            return new RespBean("error", "添加轮播图失败");
        }
    }
}
