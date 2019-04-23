package links2.driver.connection;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public abstract class LinksConnection {

	protected MongoClient mongoClient;
	protected MongoDatabase db;

	public MongoCollection<Document> getOrCreateExperiment(String experimentName, boolean override) {
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

	public void close() {
		mongoClient.close();
	}

}
