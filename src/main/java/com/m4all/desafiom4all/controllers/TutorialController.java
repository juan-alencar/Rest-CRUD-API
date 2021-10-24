package com.m4all.desafiom4all.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m4all.desafiom4all.models.entities.Tutorial;
import com.m4all.desafiom4all.models.repositories.TutorialRepository;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

	@Autowired
	private TutorialRepository tutorialRepository;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public @ResponseBody Tutorial newTutorial(@Valid Tutorial tutorial) {
		tutorialRepository.save(tutorial);
		return tutorial;
	}

	@GetMapping
	public Iterable<Tutorial> getAllTutorials() {
		return tutorialRepository.findAll();
	}

	@GetMapping(path = "/page/{pageNumber}")
	public Iterable<Tutorial> getTutorialsPerPage(@PathVariable int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 4); // Page Number and Number of items per page
		return tutorialRepository.findAll(page);
	}

	@GetMapping(path = "/{id}")
	public Optional<Tutorial> getTutorialById(@PathVariable int id) {
		return tutorialRepository.findById(id);
	}

	@GetMapping(path = "/title={keyword}")
	public Iterable<Tutorial> findByTitleContaining(@PathVariable String keyword) {
		return tutorialRepository.findByTitleContainingIgnoreCase(keyword);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteTutorialById(@PathVariable int id) {
		tutorialRepository.deleteById(id);
	}

	@DeleteMapping
	public void deleteAllTutorials() {
		tutorialRepository.deleteAll();
	}

	@GetMapping("/published")
	public Iterable<Tutorial> findByPublished() {
		Iterable<Tutorial> tutorials = tutorialRepository.findByPublished(true);
		return tutorials;
	}

}
