package de.sbeddig.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.List;

public class DynamoDbGroceryConverter implements DynamoDBTypeConverter<String, List<Grocery>> {

    @SneakyThrows
    @Override
    public String convert(List<Grocery> objects) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(objects);
    }

    @SneakyThrows
    @Override
    public List<Grocery> unconvert(String objectsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(objectsString, new TypeReference<>() {
        });
    }
}