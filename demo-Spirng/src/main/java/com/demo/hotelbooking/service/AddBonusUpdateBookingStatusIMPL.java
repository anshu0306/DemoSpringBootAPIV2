package com.demo.hotelbooking.service;

import java.util.Date;
import java.util.List;
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
public class AddBonusUpdateBookingStatusIMPL implements AddBonusUpdateBookingStatus{
	@Autowired
	private UserDeatailsRepository userDeatailsRepository;

	@Autowired
	private HotelBookingStatusRepository hotelBookingStatusRepository;
	@Override
	public String addBonusUpdateStatus(String userId, Integer bonusPoints) {
		
		UserDetailsEntity ud = 	null;
		String returnval = "";
		int updatedPoints = 0;
		List<HotelBookingStatusEntity> listHotel = null;
		try {
			Optional<UserDetailsEntity> userEntity = userDeatailsRepository.findById(userId);
			if(userEntity.isPresent()) {
				ud= userEntity.get();
				updatedPoints = ud.getUserPoints()+bonusPoints;
				listHotel= hotelBookingStatusRepository.findByLastmodifiedby(userId);
				
				if(null !=listHotel && (!listHotel.isEmpty())) {
					
					for(HotelBookingStatusEntity entity : listHotel) {

						if(entity.getBookingPoints()<=updatedPoints) {
							entity.setBookingStatus(HotelManagementConstant.BOOKED);
							entity.setLastUpdatedDate(new Date());
							updatedPoints = updatedPoints-entity.getBookingPoints();
							returnval=HotelManagementConstant.UPDATED;
						}
					}
					hotelBookingStatusRepository.saveAll(listHotel);
					ud.setUserPoints(updatedPoints);
					userDeatailsRepository.save(ud);
				}else {
					ud.setUserPoints(updatedPoints);
					userDeatailsRepository.save(ud);
					throw new HotelBookingException(HotelManagementConstant.NOT_INPENDING_STATE+userId,HttpStatus.NO_CONTENT);
				}
				
			}else 
				throw new HotelBookingException(HotelManagementConstant.USERNOTPRESENT,HttpStatus.NO_CONTENT);
		}catch(HotelBookingException hb) {
			throw new HotelBookingException(hb.getMessage(),hb.getCode());
		}catch(Exception e) {
			throw new HotelBookingException(e.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
		}finally {
			hotelBookingStatusRepository.flush();
			userDeatailsRepository.flush();
		}
		return returnval;
	}

}

