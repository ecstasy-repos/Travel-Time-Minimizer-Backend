package com.example.demo.dao;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.SourceDestination;

public interface DistanceMatrixRepository extends JpaRepository<SourceDestination, Integer>
{
	@Query("Select DISTINCT b from SourceDestination b where b.from LIKE %?1%")
	public List<SourceDestination>findByFrom(String from, Pageable paging);
	
	@Query("Select DISTINCT b from SourceDestination b where b.to LIKE %?1%")
	public List<SourceDestination>findByTo(String to, Pageable paging);
	
	@Query("Select DISTINCT b from SourceDestination b where b.from LIKE %?1% AND b.to LIKE %?2%")
	public List<SourceDestination>findByFromTo(String from, String to, Pageable paging);
	
	@Query("Select DISTINCT b from SourceDestination b where b.from LIKE %?1%")
	public List<SourceDestination>findAllDefault(String from, Pageable paging);
		
	
}
