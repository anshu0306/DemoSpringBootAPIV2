package com.demo.hotelbooking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hoteBookingException.HotelBookingException;
import com.demo.hotelManagement.util.HotelManagementConstant;
import com.demo.hotelbooking.service.AddBonusUpdateBookingStatus;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-15T14:31:18.615438+05:30[Asia/Calcutta]")
@RestController
public class UpdateBonusBookingstatusApiController implements UpdateBonusBookingstatusApi {

	private static final Logger log = LoggerFactory.getLogger(UpdateBonusBookingstatusApiController.class);
	@Autowired
	private AddBonusUpdateBookingStatus updateService;


	public ResponseEntity<Object> updateBonusBookingStatus(@ApiParam(value = "Supported Content Type- application/json" ,required=true) @RequestHeader(value="Accept", required=true) String accept
			,@ApiParam(value = "Provides the userId who wants to do the bookings",required=true) @PathVariable("userId") String userId
			,@ApiParam(value = "Provides the hotelId for which booking request has been raised",required=true) @PathVariable("bonusPoints") Integer bonusPoints
			) {
		if (accept == null || !(accept.contains("application/json"))) 
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		else if(bonusPoints < 0 || bonusPoints == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", accept);
		String status = null;
		try {
			status = updateService.addBonusUpdateStatus(userId,bonusPoints);
			if(status.equals(HotelManagementConstant.UPDATED))
				return new ResponseEntity<>(status,responseHeaders,HttpStatus.CREATED);
			else
				return new ResponseEntity<>(status,responseHeaders,HttpStatus.OK);

		} catch (HotelBookingException e) {
			return new ResponseEntity<>(e.getMessage(),responseHeaders,e.getCode());
		}catch(Exception e) {
			log.error("Exception in UpdateBonusBookingController", e);
			return new ResponseEntity<>(e.getMessage(),responseHeaders,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

