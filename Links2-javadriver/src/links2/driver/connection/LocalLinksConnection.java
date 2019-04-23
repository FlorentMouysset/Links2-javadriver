package links2.driver.connection;

import com.mongodb.MongoClient;

public class LocalLinksConnection extends LinksConnection {

    /**
     * The name of the link2 data base used by default.
     */
    public static final String          DEFAULT_DB_NAME    = "links2";
    private static final int            DEFAULT_MONGO_PORT = 27017;
    private static LocalLinksConnection connexion;

    private LocalLinksConnection(String dbName) {
		mongoClient = null;
		mongoClient = new MongoClient("localhost", DEFAULT_MONGO_PORT);
        db = mongoClient.getDatabase(dbName);
	}

	public static LocalLinksConnection getLocalConnexion() {
        return getLocalConnexion(DEFAULT_DB_NAME);
	}

    public static LocalLinksConnection getLocalConnexion(String dbName) {
        if (null == connexion) {
            connexion = new LocalLinksConnection(dbName);
        }
        return connexion;
    }

}
