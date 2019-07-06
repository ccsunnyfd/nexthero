package com.nexthero.demo.repository;

import com.nexthero.demo.model.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MovieInfoRepository
 *
 * @version 1.0
 */
public interface MovieInfoRepository extends JpaRepository<MovieInfo, Long> {

}
