package com.slogr.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.slogr.core.User;
import org.bson.Document;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all the methods for crud ops with the User collection/object
 *
 * @author vatsalgame
 */
public class CrudUser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudUser.class);

    private static MongoDriver driver = new MongoDriver();
    private static StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    /**
     * Add the given user to the users collection
     * @param user to be added
     */
    public void createUser(User user) {
        MongoDatabase db = driver.getDb();
        MongoCollection<Document> userCollection = db.getCollection("users");

        Document document = new Document("email", user.getEmail());

        // Encrypt the password before storing it
        String encryptedPassword = passwordEncryptor.encryptPassword(user.getPassword());
        document.append("password", encryptedPassword);

        userCollection.insertOne(document);
    }

    /**
     * Return whether the user credentials are valid or not
     * @param user to be validated
     * @return validity of user credentials
     */
    public boolean loginUser(User user) {
        MongoDatabase db = driver.getDb();

        Document document = db.getCollection("users").find(eq("email", user.getEmail())).first();

        // Check if the user credentials are correct and return the result
        return document != null && passwordEncryptor.checkPassword(user.getPassword(), document.getString("password"));
    }

    public List<User> getAllUsers() {
        MongoDatabase db = driver.getDb();
        MongoCollection<Document> userCollection = db.getCollection("users");
        MongoCursor<Document> userCursor = userCollection.find().iterator();

        List<User> users = new ArrayList<User>();

        try {
            while(userCursor.hasNext()) {
                Document userDocument = userCursor.next();
                users.add(new User(userDocument.getString("email"), userDocument.getString("password")));
            }
        } finally {
            userCursor.close();
        }

        return users;
    }

}
