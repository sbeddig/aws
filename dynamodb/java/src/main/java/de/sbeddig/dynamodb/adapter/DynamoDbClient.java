package de.sbeddig.dynamodb.adapter;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import de.sbeddig.dynamodb.model.Article;
import org.springframework.stereotype.Component;

@Component
public class DynamoDbClient {

    private final DynamoDBMapper mapper;

    public DynamoDbClient(AmazonDynamoDB client) {
        this.mapper = new DynamoDBMapper(client);
    }

    public void addArticle(Article article) {
        mapper.save(article);
    }

    public Article getArticle(String primaryKey, String sortKey) {
        return mapper.load(Article.class, primaryKey, sortKey);
    }

    public void deleteArticle(String primaryKey, String sortKey) {
        Article article = getArticle(primaryKey, sortKey);
        if (article != null)
            mapper.delete(article);
    }
}
