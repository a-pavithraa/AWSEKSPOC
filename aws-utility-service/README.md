This microservice is for adding and fetching records in DynamoDB Table BookmarkedHotels. WebIdentityToken is used since service account with IAM role for accessing DynamoDB has been created. 

To run locally, SystemPropertyCredentialsProvider can be used in place of WebIdentityToken (https://stackoverflow.com/questions/22588733/unable-to-load-aws-credentials-from-the-awscredentials-properties-file-on-the-c)

```
@Value("${aws.accessKeyId}")
private String accessKey;

@Value("${aws.secretKey}")
private String secretKey;

@PostConstruct
public void setSystemProperty(){
    SystemPropertiesCredentialsProvider systemPropertiesCredentialsProvider=new SystemPropertiesCredentialsProvider();

    System.setProperty("aws.accessKeyId",accessKey);
    System.setProperty("aws.secretAccessKey",secretKey);
}
```

In application.properties

```
aws.accessKeyId=
aws.secretKey=
```

Further changes would be made to add SNS notification when records are added
