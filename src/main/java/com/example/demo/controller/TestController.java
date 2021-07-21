package com.example.demo.controller;
import com.example.demo.dao.DistanceMatrixRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.example.demo.entities.PageResponse;
import com.example.demo.entities.SourceDestination;
import com.example.demo.services.SearchingService;

import java.io.Reader;
import java.util.List;
import java.util.Optional;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {
	
	@Autowired
	private SearchingService searchservice;
	
	@Autowired
	private DistanceMatrixRepository distmatrepo;
	
	 @RequestMapping("/")
	public PageResponse getAllSourceDestination(@RequestParam(name="to", required=false)String to ,@RequestParam(name="from", required=false)String from, @RequestParam(name="page", required=false)Integer page, @RequestParam(name="page_size",required=false)Integer pageSize,@RequestParam(name="q",required=false) String q){
		 PageResponse out = new PageResponse();
		 List<SourceDestination> res =  distmatrepo.findAll();
		
		 if(page==null && pageSize == null && q==null)
		  {
			  List<SourceDestination> response =  distmatrepo.findAll();
			  
			  out.setContent(response);
			  out.setCurrentPage(1);
			  out.setTotalItems(response.size());
			  out.setTotalPages(res.size());
			  return out;
		  }
		
		  else if(page != null && pageSize == null)
		  {
			  pageSize = 5;
		  }
		  else if(page == null && pageSize != null)
		  {
			  page = 1;
		  }
		
	  if(q == null){
		  
		  if(from == null && to == null)
		  {
			  Pageable paging = PageRequest.of(page-1, pageSize);
			  List<SourceDestination> response = (List<SourceDestination>)distmatrepo.findAllDefault("", paging);
			  out.setContent(response);
			  out.setCurrentPage(page);
			  out.setTotalItems(response.size());
			  out.setTotalPages(res.size());
			  return out;
		  }
		  else if(from == null && to !=null)
		  {
			  Pageable paging = PageRequest.of(page-1, pageSize);
			  List<SourceDestination> response = distmatrepo.findByTo(to, paging);
			  out.setContent(response);
			  out.setCurrentPage(page);
			  out.setTotalItems(response.size());
			  out.setTotalPages(res.size());
			  return out;
		  }
		  else if(from !=null && to == null)
		  {
			  Pageable paging = PageRequest.of(page-1, pageSize);
			  List<SourceDestination> response = distmatrepo.findByFrom(from, paging);
			  out.setContent(response);
			  out.setCurrentPage(page);
			  out.setTotalItems(response.size());
			  out.setTotalPages(res.size());
			  return out;
		  }
		  else {
			  Pageable paging = PageRequest.of(page-1, pageSize);
			  List<SourceDestination> response = distmatrepo.findByFromTo(from, to, paging);
			  out.setContent(response);
			  out.setCurrentPage(page);
			  out.setTotalItems(response.size());
			  out.setTotalPages(res.size());
			  return out;
		  }
		
	  }
	  else {
		  if(page == null)
		  {
			page = 1;
		  }
		  if(pageSize == null)
		  {
			  pageSize = 5;
		  }
		  System.out.println("page: " + page + " pageSize: " + pageSize);
		  Page<SourceDestination> response = searchservice.getUniversalSearchResults(page, pageSize, q);
		  out.setContent(response.getContent());
		  out.setCurrentPage(page);
		  out.setTotalItems(response.getContent().size());
		  out.setTotalPages(res.size());
		  return out;
	  }
	
	 }
	 
	 @PostMapping("/add")
	 public void addEntity(@RequestBody SourceDestination srcdes) {
		 
		 Pageable paging = PageRequest.of(0, 15);
		  List<SourceDestination> response = distmatrepo.findByFromTo(srcdes.getFrom(), srcdes.getTo(), paging);
		  if(response.isEmpty())
		  {
			  distmatrepo.save(srcdes);	  
			  System.out.println("Added succesfully");
		  }else {
			  System.out.println("Already present");  
		  }
	 }
	 
	 
	 @PutMapping("/update")
	 public void editEntity(@RequestBody SourceDestination srcdes0) {
			 
			  distmatrepo.save(srcdes0);
	 }
	 
	 @DeleteMapping("/srcdes/{id}")
	 public void deleteEntity(@PathVariable int id) {
		 Optional<SourceDestination> srcdes =  distmatrepo.findById(id);
		 if(srcdes.isPresent())
		 {
			 distmatrepo.delete(srcdes.get());
		 }
		 else {
			 System.out.println("Not present!!,Cannot delete!!");
		 }
	 }
	 
	 @PostMapping(path = "/upload-csv")
		public void uploadAccount(@RequestParam("file") MultipartFile file) {

			if (file.isEmpty()) {
				return;
			} else {

				// parse CSV file to create a list of `User` objects
				try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

					// create csv bean reader
					CsvToBean<SourceDestination> csvToBean = new CsvToBeanBuilder<SourceDestination>(reader).withType(SourceDestination.class)
							.withIgnoreLeadingWhiteSpace(true).build();

					// convert `CsvToBean` object to list of users
					List<SourceDestination> accounts = csvToBean.parse();

					for (int i = 0; i < accounts.size(); i++) {
						accounts.get(i).setId(i);
					}
					distmatrepo.saveAll(accounts);
				} catch (Exception ex) {
					return;
				}
			}

		}
}


