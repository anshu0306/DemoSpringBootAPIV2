package com.demo.hotelbooking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="hotelbookingstatus")
@Entity(name="hotelbookingstatus")
public class HotelBookingStatusEntity {
	
	@Id
	@Column(name = "hotelid")
	private String  hotelId;
	@Column(name = "hotename")
	private String hoteName; 
	@Column(name = "bookingpoints")
	private Integer bookingPoints;
	@Column(name = "bookingstatus")
	private  String bookingStatus;
	@Column(name = "lastupdateddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedDate;
	@Column(name = "lastmodifiedby")
	private String lastModifiedBy;
	
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHoteName() {
		return hoteName;
	}
	public void setHoteName(String hoteName) {
		this.hoteName = hoteName;
	}
	public Integer getBookingPoints() {
		return bookingPoints;
	}
	public void setBookingPoints(Integer bookingPoints) {
		this.bookingPoints = bookingPoints;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	
	

}
