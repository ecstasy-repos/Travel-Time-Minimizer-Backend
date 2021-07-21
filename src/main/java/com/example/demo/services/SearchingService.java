package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DistanceMatrixRepository;
import com.example.demo.entities.SourceDestination;


@Service
public class SearchingService {
   
	@Autowired
	private DistanceMatrixRepository distmatrepo;
	
	public Page<SourceDestination> getUniversalSearchResults(int page, int pageSize, String q) {
		SourceDestination account = new SourceDestination();
		account.setAll(q);
		Pageable pageableInstance = PageRequest.of(page-1, pageSize);
		Example<SourceDestination> universalSearchExample = Example.of(account,generateExampleMatcher(ExampleMatcher.matchingAny()));
		return distmatrepo.findAll(universalSearchExample, pageableInstance);
	}
	
	public Page<SourceDestination> getCompoundSearchResults(int page, int pageSize, SourceDestination account) {
		Pageable pageableInstance = PageRequest.of(page - 1, pageSize);
		Example<SourceDestination> compoundSearchExample = Example.of(account,
				generateExampleMatcher(ExampleMatcher.matchingAll()));
		return distmatrepo.findAll(compoundSearchExample, pageableInstance);
	}
		
	private ExampleMatcher generateExampleMatcher(ExampleMatcher searchMatcher) {
		return searchMatcher.withMatcher("from", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("to", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("road_way_time", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("areal_time", ExampleMatcher.GenericPropertyMatchers.contains());
	}
}
