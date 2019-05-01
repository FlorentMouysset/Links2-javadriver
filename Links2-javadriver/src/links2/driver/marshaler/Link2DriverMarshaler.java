package links2.driver.marshaler;

import java.util.List;

import com.mongodb.client.MongoCollection;

import links2.driver.Links2Driver;
import links2.driver.connection.LinksConnection;
import links2.driver.model.Experiment;
import links2.driver.model.Snapshot;

public class Link2DriverMarshaler extends Links2Driver {

    private Link2DriverMarshaler() {
    }

    public static void marshalling(LinksConnection connection, Experiment experiment, MarshallingMode marshallingMode) {
        String experimentName = experiment.getExperimentName();
        boolean experimentAlreadyExist = connection.experimentExist(experimentName);
        final boolean overrideExistingExperiment = MarshallingMode.OVERRIDE_EXP_IF_EXISTING == marshallingMode;
        MongoCollection<?> dbCollectionExp = connection.getOrCreateCollection(experimentName,
                overrideExistingExperiment);

        if (experimentAlreadyExist && overrideExistingExperiment) {
            MongoCollection<Experiment> collection = dbCollectionExp.withDocumentClass(Experiment.class)
                    .withCodecRegistry(jacksonCodecRegistry);

            collection.insertOne(experiment);
        }

        MongoCollection<Snapshot> collectionSnapshot = dbCollectionExp.withDocumentClass(Snapshot.class)
                .withCodecRegistry(jacksonCodecRegistry);

        final List<Snapshot> snapshots = experiment.getSnapshots();
        if (snapshots != null && !snapshots.isEmpty()) {
            collectionSnapshot.insertMany(snapshots);
        }
    }

}
