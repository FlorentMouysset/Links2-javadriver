package links2.driver.connection;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public abstract class LinksConnection {

	protected MongoClient mongoClient;
	protected MongoDatabase db;

	public MongoCollection<Document> getOrCreateCollection(String experimentName, boolean override) {
		if (override) {
			removeExperiment(experimentName);
		}

		return db.getCollection(experimentName);
	}

	public void removeExperiment(String experimentName) {
        MongoCollection<Document> expToRm = db.getCollection(experimentName);
        if (null != expToRm) {
			expToRm.drop();
        }
	}
	
	public boolean experimentExist(String experimentName) {
	    MongoCollection<Document> expToRm = db.getCollection(experimentName);
	    return null != expToRm;
	}

	public void close() {
		mongoClient.close();
	}

}
