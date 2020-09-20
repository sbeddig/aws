package de.sbeddig.dynamodb;

import de.sbeddig.dynamodb.adapter.DynamoDbClient;
import de.sbeddig.dynamodb.model.Article;
import de.sbeddig.dynamodb.model.Grocery;
import de.sbeddig.dynamodb.model.Quantity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DynamoDbApplicationTests {

	@Autowired
	private DynamoDbClient dynamoDbClient;

	private static final String PRIMARY_KEY = "name";
	private static final String SORT_KEY = "manufacturer";

	@Test
	void saveArticle() {
		Article article = aArticle();
		dynamoDbClient.addArticle(article);

		Article articleFromDynamoDb = dynamoDbClient.getArticle(PRIMARY_KEY, SORT_KEY);

		assertEquals(article, articleFromDynamoDb);
	}

	private Article aArticle() {
		Article article = new Article();
		article.setName(PRIMARY_KEY);
		article.setManufacturer(SORT_KEY);
		article.getGroceries().add(aGrocery());
		return article;
	}

	private Quantity aQuantity() {
		Quantity quantity = new Quantity();
		quantity.setUnit("L");
		quantity.setValue(1);
		return quantity;
	}

	private Grocery aGrocery() {
		Grocery grocery = new Grocery();
		grocery.setName("NP");
		grocery.setPrice(0.65f);
		grocery.setQuantity(aQuantity());
		return grocery;
	}

}
