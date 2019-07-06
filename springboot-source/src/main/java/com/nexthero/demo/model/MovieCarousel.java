package com.nexthero.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * MovieCarousel
 *
 * @version 1.0
 */
@Getter
@Setter
@Entity
public class MovieCarousel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @Column(name = "movieId", nullable = false, length = 30)
    private String movieId;

    @Column(name = "image", nullable = false, length = 200)
    private String image;

    @Column(name = "sort", nullable = false, length = 10)
    private Integer sort;

    @Column(name = "isShow", length = 1, insertable = false, columnDefinition = "int default 1")
    private Integer isShow;
}
