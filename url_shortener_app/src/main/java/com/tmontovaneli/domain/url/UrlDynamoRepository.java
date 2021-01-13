package com.tmontovaneli.domain.url;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.tmontovaneli.infrastructure.annotation.Dynamo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
@Dynamo
public class UrlDynamoRepository implements IURLRepository {

    private static String _URL = "URL_";
    private static String _ID = "ID";

    @Inject
    private AmazonDynamoDB dynamoDB;

    @Override
    public void save(URL url) {
        getTable("URL_KEY")
                .putItem(new Item()
                        .withPrimaryKey(_URL, url.url())
                        .withString(_ID, url.key()));

        getTable("KEY_URL")
                .putItem(new Item()
                        .withPrimaryKey(_ID, url.key())
                        .withString(_URL, url.url()));
    }

    @Override
    public Optional<URL> findByKey(String key) {
        try {
            Item item = getTable("KEY_URL")
                    .getItem(_ID, key);

            if(item == null) return Optional.empty();

            return Optional.of(URL.create(item.getString(_URL),
                    item.getString(_ID)));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<URL> findByURL(String url) {
        try {
            Item item = getTable("URL_KEY").getItem(_URL, url);

            if(item == null)
                return Optional.empty();

            return Optional.of(URL.create(item.getString(_URL),
                    item.getString(_ID)));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Table getTable(String name) {
        DynamoDB dynamoDB = new DynamoDB(this.dynamoDB);
        return dynamoDB.getTable(name);
    }
}
