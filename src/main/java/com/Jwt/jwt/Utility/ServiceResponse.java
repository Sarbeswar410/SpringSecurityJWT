package com.Jwt.jwt.Utility;

import lombok.Data;

@Data
public class ServiceResponse {
	private Object serviceResponse;
	private String serviceStatus;
	private Object serviceError;
	
	public static final String STATUS_FAIL="Fail";
	public static final String STATUS_SUCCESS="Success";
	public static final String PUBLISHED="PUBLISHED";
	public static final String DATA_NOT_FOUND="Data Not Found";
	public static final String SOMETHING_WENT_WRONG="Something Went Wrong";

}
