package com.awsservice.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;

@Data
@DynamoDBTable(tableName = "BookmarkedHotels")
public class BookmarkedHotels {
	@DynamoDBHashKey
	private String username;
	private List<HotelDetails> hotelDetails;

}
