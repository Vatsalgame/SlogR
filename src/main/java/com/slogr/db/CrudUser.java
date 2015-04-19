package com.slogr.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.slogr.core.User;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class containing all the methods for crud ops with the User collection/object
 *
 * @author vatsalgame
 */
public class CrudUser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudUser.class);

    private static MongoDriver driver = new MongoDriver();

    public void createUser(User user) {
        MongoDatabase db = driver.getDb();
        MongoCollection<Document> userCollection = db.getCollection("users");

        Document document = new Document("email", user.getEmail());
        document.append("password", user.getPassword());

        userCollection.insertOne(document);
    }

}
