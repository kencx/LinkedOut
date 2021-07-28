package com.fdm.proj.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdm.proj.service.SearchService;

@Controller
public class SearchController {

	private static final Logger INFO = LogManager.getLogger(LoginController.class);
	private static final Logger ERROR = LogManager.getLogger(LoginController.class);
	
	private SearchService searchService;
	
	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String goToSearchPage() {
		return "search";
	}
	
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String goToSearchedTag() {
		
		// accept tag inputs
		
		// DAO search for tags
		
		// return obtained posts
		
		return "search";
	}
}
