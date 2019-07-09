package com.nexthero.demo.repository;

import com.nexthero.demo.model.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * MovieInfoRepository
 *
 * @version 1.0
 */
public interface MovieInfoRepository extends JpaRepository<MovieInfo, Long>, JpaSpecificationExecutor {

}
