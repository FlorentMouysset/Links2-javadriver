package links2.driver.connection;

import com.mongodb.MongoClient;

public class LocalLinksConnection extends LinksConnection {

	private static LocalLinksConnection connexion;

	private LocalLinksConnection() {
		mongoClient = null;
		mongoClient = new MongoClient("localhost", 27017);
		db = mongoClient.getDatabase("links2");
	}

	public static LocalLinksConnection getLocalConnexion() {
		if (null == connexion) {
			connexion = new LocalLinksConnection();
		}
		return connexion;
	}

}
