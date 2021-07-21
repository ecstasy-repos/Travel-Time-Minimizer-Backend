package com.example.demo.entities;

import java.util.List;


public class PageResponse {
	private int totalItems;
	private int totalPages;
	private int currentPage;
	private List<SourceDestination> content;
	
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<SourceDestination> getContent() {
		return content;
	}
	public void setContent(List<SourceDestination> content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "PageResponse [totalItems=" + totalItems + ", totalPages=" + totalPages + ", currentPage=" + currentPage
				+ ", content=" + content + "]";
	}
	public PageResponse(int totalItems, int totalPages, int currentPage, List<SourceDestination> content) {
		super();
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.content = content;
	}
	public PageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
