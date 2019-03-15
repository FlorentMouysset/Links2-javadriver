package links2.driver.marshaler;

import com.mongodb.client.MongoCollection;

import links2.driver.Links2Driver;
import links2.driver.connection.LinksConnection;
import links2.driver.model.Experiment;
import links2.driver.model.Snapshot;

public class Link2DriverMarshaler extends Links2Driver {

	private Link2DriverMarshaler() {
	}

	public static void marshalling(LinksConnection connection, Experiment experiment, MarshallingMode marshallingMode) {
		MongoCollection<?> dbCollectionExp =
		 connection.getOrCreateExperiment(experiment.getExperimentName(),
		 MarshallingMode.OVERRIDE_EXP_IF_EXISTING.equals(marshallingMode));
		
		MongoCollection<Experiment> collection = dbCollectionExp.withDocumentClass(Experiment.class)
				.withCodecRegistry(jacksonCodecRegistry);
		
		collection.insertOne(experiment);

		MongoCollection<Snapshot> collectionSnapshot = dbCollectionExp.withDocumentClass(Snapshot.class)
				.withCodecRegistry(jacksonCodecRegistry);

		collectionSnapshot.insertMany(experiment.getSnapshots());
	}

}
