package com.slogr.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class for mongo DB
 *
 * @author vatsalgame
 */
public class MongoDriver {

    // Major TODO: Clean up this driver class once done experimenting

    // Client used to connect to mongo db
    // TODO: Modify this to be read from a setting file
    private static MongoClient mongoClient = new MongoClient("localhost" , 27017);

    // DB that is currently being used. Consider moving this to an appropriate method
    private static MongoDatabase db = mongoClient.getDatabase("mydb");

    // TODO: Remove this dummy method
    public MongoCollection<Document> getTestDataCollection() {
        MongoCollection<Document> collection = db.getCollection("testData");
        return collection;
    }

    /**
     * @return all the documents contained in the testData collection
     */
    public List<Document> getTestData() {
        MongoCollection<Document> collection = db.getCollection("testData");
        MongoCursor<Document> cursor = collection.find().iterator();

        List<Document> documents = new ArrayList<Document>();

        try {
            while(cursor.hasNext()) {
                documents.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return documents;
    }

}
