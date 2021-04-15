package com.demo.hotelbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.hotelbooking.entity.HotelBookingStatusEntity;

@Repository
public interface HotelBookingStatusRepository extends JpaRepository<HotelBookingStatusEntity,String>{

	@Query("select hbs from hotelbookingstatus hbs where hbs.lastModifiedBy =?1 and hbs.bookingStatus='Pending Approval' order by lastUpdatedDate")
	public List<HotelBookingStatusEntity> findByLastmodifiedby(String user);

}
