package com.hotelbooking.model.hotels;

import java.io.Serializable;

import lombok.Data;

@Data
public class AllInclusiveAmount implements Serializable{
	private double value;
    private String currency;
}
