package com.awsservice.config;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;

import org.springframework.stereotype.Component;

import com.awsservice.model.BookmarkedHotels;
import com.awsservice.model.HotelDetails;



@Component("DynamoDBEnhanced")
public class DynamoDbEnhanced {
	
	  private DynamoDbEnhancedClient getClient() {
		  Region region = Region.US_EAST_1;
	        DynamoDbClient ddb = DynamoDbClient.builder()
	                .region(region)
	                .credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
	                .build();
	        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(ddb)
                    .build();



	        return enhancedClient;
	    }
	  
	public BookmarkedHotels getHotels(String userName) {
		DynamoDbEnhancedClient ddb = getClient();
		 //Get the Key object.
        Key key = Key.builder()
                .partitionValue(userName)
                .build();
		DynamoDbTable<BookmarkedHotels> custTable = ddb.table("BookmarkedHotels", TableSchema.fromBean(BookmarkedHotels.class));
		return custTable.getItem(key);
	}
	   // Uses the Enhanced Client to inject a new post into a DynamoDB table
    public void injectDynamoItem(BookmarkedHotels hotel){

     
        try {

            DynamoDbEnhancedClient enhancedClient = getClient();

            //Create a DynamoDbTable object
            DynamoDbTable<BookmarkedHotels> mappedTable = enhancedClient.table("BookmarkedHotels", TableSchema.fromBean(BookmarkedHotels.class));
           
            PutItemEnhancedRequest<BookmarkedHotels> enReq = PutItemEnhancedRequest.builder(BookmarkedHotels.class)
                    .item(hotel)
                    .build();

            mappedTable.putItem(enReq);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

   

}
