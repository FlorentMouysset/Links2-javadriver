package links2.driver.unmarshaler;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import links2.driver.Links2Driver;
import links2.driver.connection.LinksConnection;
import links2.driver.model.Experiment;
import links2.driver.model.Snapshot;

public class Link2DriverUnmarshaler extends Links2Driver {

	private Link2DriverUnmarshaler() {
	}

	public static Experiment unmarshalling(LinksConnection connection, String experimentName) {
		MongoCollection<?> dbCollectionExp =
				connection.getOrCreateExperiment(experimentName, false);
		
		MongoCollection<Experiment> collection = dbCollectionExp.withDocumentClass(Experiment.class)
				.withCodecRegistry(jacksonCodecRegistry);
		
		FindIterable<Experiment> ite = collection.find();
		Experiment exp = ite.first();
		if (null != exp) {
			List<Snapshot> snapshots = getSnapshot(dbCollectionExp);
			exp.setSnapshots(snapshots);
		}

		return exp;
	}

	private static List<Snapshot> getSnapshot(MongoCollection<?> dbCollectionExp) {
		MongoCollection<Snapshot> collectionSnapshot = dbCollectionExp.withDocumentClass(Snapshot.class)
				.withCodecRegistry(jacksonCodecRegistry);

		int size = (int) (collectionSnapshot.countDocuments() - 1);
		List<Snapshot> result = new ArrayList<>(size);

		FindIterable<Snapshot> ite = collectionSnapshot.find(Filters.exists("snapshotNumber"));

		MongoCursor<Snapshot> cursor = ite.iterator();

		while (cursor.hasNext()) {
			Snapshot snasphot = cursor.next();
			result.add(snasphot);
		}

		cursor.close();
		return result;
	}

}
