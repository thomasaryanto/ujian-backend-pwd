package com.thomasariyanto.ujianbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thomasariyanto.ujianbackend.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
