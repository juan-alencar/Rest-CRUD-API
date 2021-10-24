package com.m4all.desafiom4all.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.m4all.desafiom4all.models.entities.Tutorial;

public interface TutorialRepository 
	extends PagingAndSortingRepository<Tutorial, Integer> {
	
	public Iterable<Tutorial> findByTitleContainingIgnoreCase(String keyword);
	public Iterable<Tutorial> findByPublished(boolean published);
	
}
