package com.nexthero.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * MovieInfo
 *
 * @version 1.0
 */
@Getter
@Setter
@Entity
public class MovieInfo {
    @Id
    @Column(name = "id", nullable = false, length = 30)
    private String id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "poster", nullable = false, length = 200)
    private String poster;

    @Column(name = "cover", nullable = false, length = 200)
    private String cover;

    @Column(name = "trailer", nullable = false, length = 200)
    private String trailer;

    @Column(name = "score", nullable = false, length = 2)
    private Integer score;  //精确到小数点后一位，输入输出做处理

    @Column(name = "prisedCounts")
    private Integer prisedCounts;

    @Column(name = "basicInfo", nullable = false, length = 30)
    private String basicInfo;

    @Column(name = "originalName", length = 30)
    private String originalName;

    @Column(name = "releaseDate", length = 40)
    private String releaseDate;

    @Column(name = "totalTime", length = 40)
    private String totalTime;

    @Column(name = "plotDesc")
    private String plotDesc;
}
