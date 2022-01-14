package com.awsservice.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.awsservice.model.BookmarkedHotels;


@EnableScan
public interface BookmarkedHotelsRepository extends CrudRepository<BookmarkedHotels, String>{

}
