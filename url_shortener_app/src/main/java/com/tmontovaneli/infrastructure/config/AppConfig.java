package com.tmontovaneli.infrastructure.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import javax.enterprise.inject.Produces;

public class AppConfig {

    @Produces
    public AmazonDynamoDB buildDynamo() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-west-2")
                ).build();
    }
}
