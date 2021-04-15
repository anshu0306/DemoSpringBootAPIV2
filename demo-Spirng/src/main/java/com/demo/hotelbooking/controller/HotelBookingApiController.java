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
import com.demo.hotelbooking.service.HotelBookingService;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-13T23:13:46.829970+05:30[Asia/Calcutta]")
@RestController
public class HotelBookingApiController implements HotelBookingApi {

	@Autowired
	private HotelBookingService hotelBookingService;
	private static final Logger log = LoggerFactory.getLogger(HotelBookingApiController.class);


	public ResponseEntity<Object> bookingOperation(@ApiParam(value = "Supported Content Type- application/json" ,required=true) @RequestHeader(value="Accept", required=true) String accept
			,@ApiParam(value = "Provides the userId who wants to do the bookings",required=true) @PathVariable("userId") String userId
			,@ApiParam(value = "Provides the hotelId for which booking request has been raised",required=true) @PathVariable("hotelId") String hotelId
			) {if (accept == null || !(accept.contains("application/json"))) {
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Content-Type", accept);
			try {
				String status = hotelBookingService.doBooking(userId,hotelId);
				if(status.equals(HotelManagementConstant.BOOKED))
					return new ResponseEntity<>(status,responseHeaders,HttpStatus.OK);
				else
					return new ResponseEntity<>(status,responseHeaders, HttpStatus.CREATED);
				
			} catch(HotelBookingException e) {
				log.error("Something happend at Controller layer inside hotel Exception", e);
				return new ResponseEntity<>(e.getMessage(),responseHeaders,e.getCode());
			}catch (Exception e) {
				log.error("Something happend at Controller layer inside main Exception", e);
				return new ResponseEntity<Object>(e.getMessage(),responseHeaders,HttpStatus.SERVICE_UNAVAILABLE);
			}
	}

}
