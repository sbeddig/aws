package de.sbeddig.dynamodb.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfiguration {

    @Value("${AWS_DYNAMO_DB_ACCESS_KEY}")
    private String access_key;

    @Value("${AWS_DYNAMO_DB_SECRET_ACCESS_KEY}")
    private String secret_access_key;

    @Value("${AWS_DYNAMO_DB_REGION}")
    private String region;

    @Value("${AWS_DYNAMO_DB_URL}")
    private String url;

    @Bean
    public AmazonDynamoDB getDynamoDbClient() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(access_key, secret_access_key);
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(url, region))
                .build();
    }

}
