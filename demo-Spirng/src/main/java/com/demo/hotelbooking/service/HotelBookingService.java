package com.demo.hotelbooking.service;

import com.demo.hoteBookingException.HotelBookingException;

public interface HotelBookingService {

	String doBooking(String userId, String hotelId) throws HotelBookingException;

}
