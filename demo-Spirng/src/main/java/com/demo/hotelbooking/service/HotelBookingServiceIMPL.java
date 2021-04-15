package com.demo.hotelbooking.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.hoteBookingException.HotelBookingException;
import com.demo.hotelManagement.util.HotelManagementConstant;
import com.demo.hotelbooking.entity.HotelBookingStatusEntity;
import com.demo.hotelbooking.entity.UserDetailsEntity;
import com.demo.hotelbooking.repository.HotelBookingStatusRepository;
import com.demo.hotelbooking.repository.UserDeatailsRepository;

@Service
public class HotelBookingServiceIMPL implements HotelBookingService{
	@Autowired
	private UserDeatailsRepository userDeatailsRepository;

	@Autowired
	private HotelBookingStatusRepository hotelBookingStatusRepository;

	@Override
	public String doBooking(String userId, String hotelId) throws HotelBookingException{
		UserDetailsEntity ud = 	null;
		HotelBookingStatusEntity hbs = null;
		String returnval = "unknown";
		int updatedPoints ;
		try {
			Optional<HotelBookingStatusEntity> hotelEntity = 
					hotelBookingStatusRepository.findById(hotelId);
			Optional<UserDetailsEntity> userEntity = userDeatailsRepository.findById(userId);
			if(hotelEntity.isPresent() && userEntity.isPresent()) {
				ud = userEntity.get();
				hbs = hotelEntity.get();
				if(ud.getUserPoints() >= hbs.getBookingPoints() && 
						hbs.getBookingStatus().equals(HotelManagementConstant.PENDING_APPROVAL) || 
							hbs.getBookingStatus().equals(HotelManagementConstant.AVAILABLE)) {
						
						returnval = HotelManagementConstant.BOOKED;
						updatedPoints = ud.getUserPoints()- hbs.getBookingPoints();

				}else if(ud.getUserPoints() < hbs.getBookingPoints() && hbs.getBookingStatus().equals(HotelManagementConstant.AVAILABLE)){
					
					returnval = HotelManagementConstant.PENDING_APPROVAL;
					updatedPoints = ud.getUserPoints();
				}else 
					throw new HotelBookingException(HotelManagementConstant.HOTELNOTPRESENT,HttpStatus.NOT_ACCEPTABLE);
				
				hbs.setBookingStatus(returnval);
				hbs.setLastUpdatedDate(new Date());
				hbs.setLastModifiedBy(userId);
				ud.setUserPoints(updatedPoints);
				
				hotelBookingStatusRepository.saveAndFlush(hbs);
				userDeatailsRepository.saveAndFlush(ud);

			}else 
				throw new HotelBookingException(HotelManagementConstant.CONTACT_ADMIN,HttpStatus.BAD_REQUEST);
			
		}catch(Exception e) {
			throw  new HotelBookingException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return returnval;
	}
}
