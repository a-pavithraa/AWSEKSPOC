package com.hotelbooking.model.hotels;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Block implements Serializable{
	 private List<Credit> credits;
	    private String block_id;

}
