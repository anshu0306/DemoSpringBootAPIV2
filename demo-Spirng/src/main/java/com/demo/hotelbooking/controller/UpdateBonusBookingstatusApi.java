/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.22).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.demo.hotelbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-15T14:32:34.587025400+05:30[Asia/Calcutta]")
@Api(value = "UpdateBonusBookingstatus", description = "the UpdateBonusBookingstatus API")
public interface UpdateBonusBookingstatusApi {

    @ApiOperation(value = "update the bonus point and accordingly change the status of booked hotel.", nickname = "updateBonusBookingStatus", notes = "depending upon the userid's bonus point and change the status of booking and update the bonus points.", response = Object.class, tags={ "update-bonus-bookingstatus", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response", response = Object.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Object.class),
        @ApiResponse(code = 400, message = "Bad request when the user do not pass the correct input or format", response = Object.class) })
    @RequestMapping(value = "/addBonusUpdateStatus/{userId}/{bonusPoints}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Object> updateBonusBookingStatus(@ApiParam(value = "Supported Content Type- application/json" ,required=true) @RequestHeader(value="Accept", required=true) String accept
,@ApiParam(value = "Provides the userId who wants to do the bookings",required=true) @PathVariable("userId") String userId
,@ApiParam(value = "Provides the hotelId for which booking request has been raised",required=true) @PathVariable("bonusPoints") Integer bonusPoints
);

}

