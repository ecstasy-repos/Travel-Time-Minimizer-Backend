package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="source_destination")
public class SourceDestination {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
	
	@Column(name="`from`")
	private String from;
	
	@Column(name="`to`")
	private String to;
	
	@Column(name="road_way_time")
	private double road_way_time;
	
	@Column(name="areal_time")
	private double areal_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public double getRoad_way_time() {
		return road_way_time;
	}

	public void setRoad_way_time(double road_way_time) {
		this.road_way_time = road_way_time;
	}

	public double getAreal_time() {
		return areal_time;
	}

	public void setAreal_time(double areal_time) {
		this.areal_time = areal_time;
	}

	public SourceDestination() {
		super();
	}

	public SourceDestination(int id, String from, String to, double road_way_time, double areal_time) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.road_way_time = road_way_time;
		this.areal_time = areal_time;
	}

	@Override
	public String toString() {
		return "SourceDestination [id=" + id + ", from=" + from + ", to=" + to + ", road_way_time=" + road_way_time
				+ ", areal_time=" + areal_time + "]";
	}

	 public void setAll(String q) {
		// TODO Auto-generated method stub
		this.from = q;
		this.to = q;
	}
	
	
}
