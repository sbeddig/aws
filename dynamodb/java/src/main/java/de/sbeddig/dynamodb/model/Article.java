package de.sbeddig.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName="articles")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Article {

    @DynamoDBHashKey(attributeName="Name")
    private String name;

    @DynamoDBRangeKey(attributeName = "Manufacturer")
    private String manufacturer;

    @DynamoDBTypeConverted(converter = DynamoDbGroceryConverter.class)
    private List<Grocery> groceries = new ArrayList<>();

    @DynamoDBVersionAttribute
    private Long version;

}


