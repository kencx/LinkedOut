package com.fdm.proj.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.proj.model.Post;
import com.fdm.proj.service.SearchService;

@Controller
public class SearchController {

	private static final Logger INFO = LogManager.getLogger(LoginController.class);
	
	private SearchService searchService;
	
	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}
	
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String performSearch(Model model, @RequestParam String searchbar, HttpSession session) {
	
		String path = checkPath(searchbar);
		if (path != null) {
			return path;
		}
		
		performSearch(searchbar, session);
		return "redirect:/search/" + searchbar;
	}
	
	@RequestMapping(value="/search/{searchbar}", method=RequestMethod.GET)
	public String loadSearchPage(@PathVariable String searchbar, HttpSession session) {
		 
		String path = checkPath(searchbar);
		if (path != null) {
			return path;
		}
		
		performSearch(searchbar, session);
		return "search";
	}
	
	
	public void performSearch(String searchbar, HttpSession session) {
		
		List<Post> searchResult = searchService.search(searchbar);

		if (searchResult == null) {
			session.setAttribute("searchResult", searchResult); // update with empty search
			INFO.info("Search " + searchbar + " found 0 posts.");
			
		} else {
			session.setAttribute("searchResult", searchResult);
			INFO.info("Search " + searchbar + " found " + searchResult.size() + " posts.");
		}
	}
	
	
	public String checkPath(String searchbar) {
		
		// HARDCODED: if path is to other existing controller pages
		List<String> existingPaths = Arrays.asList("homefeed", "profile", "logout");
		
		if (existingPaths.contains(searchbar)) {
			return "redirect:/" + searchbar;
		}
		return null;
	}
}
