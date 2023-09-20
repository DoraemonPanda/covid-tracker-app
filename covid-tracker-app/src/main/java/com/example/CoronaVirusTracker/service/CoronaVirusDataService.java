package com.example.CoronaVirusTracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.CoronaVirusTracker.model.LocationStats;


@Service
public class CoronaVirusDataService {
	
	
	private static String str = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationStats> allStates = new ArrayList<>(); 

	public List<LocationStats> getAllStates() {
		return allStates;
	}


	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException, ServletException {
		
		List<LocationStats> newStats = new ArrayList<>(); 
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(str))
				.build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		for (CSVRecord record : records) {
			LocationStats  locationStat = new LocationStats ();
		    locationStat.setState(record.get("Province/State"));
		    locationStat.setCountry(record.get("Country/Region"));
		    int latestCases = Integer.parseInt(record.get(record.size()-1));
		    int prevDayCases = Integer.parseInt(record.get(record.size()-2));
		    locationStat.setLatestTotalCases(latestCases);
		    locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
		    
		    newStats.add(locationStat);
      
		}
		this.allStates = newStats;
				
	}


	
}
