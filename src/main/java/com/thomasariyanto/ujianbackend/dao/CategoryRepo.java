package com.thomasariyanto.ujianbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thomasariyanto.ujianbackend.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}