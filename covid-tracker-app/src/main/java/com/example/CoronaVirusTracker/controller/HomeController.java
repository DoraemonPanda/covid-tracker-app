package com.example.CoronaVirusTracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.CoronaVirusTracker.model.LocationStats;
import com.example.CoronaVirusTracker.service.CoronaVirusDataService;

@Controller	
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@RequestMapping("/")
	public ModelAndView home() {	
		
		
		ModelAndView mv = new ModelAndView();
		List<LocationStats> allStats = coronaVirusDataService.getAllStates();
		int totalReportdCases = allStats.stream().mapToInt(x -> x.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(x -> x.getDiffFromPrevDay()).sum();
		mv.addObject("location", allStats);
		mv.addObject("totalReportdCases", totalReportdCases);
		mv.addObject("totalNewCases", totalNewCases);
		mv.setViewName("home.jsp");
		return (mv);		
	}
	
		
}	

