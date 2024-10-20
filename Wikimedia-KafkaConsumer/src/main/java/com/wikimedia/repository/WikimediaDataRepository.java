package com.wikimedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wikimedia.entity.WikimediaData;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long>{

	
}
