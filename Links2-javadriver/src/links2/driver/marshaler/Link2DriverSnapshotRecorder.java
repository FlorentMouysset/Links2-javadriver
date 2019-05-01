package links2.driver.marshaler;

import java.util.function.Consumer;

import links2.driver.connection.LinksConnection;
import links2.driver.model.Experiment;
import links2.driver.model.Snapshot;

public class Link2DriverSnapshotRecorder implements Consumer<Snapshot> {

    private LinksConnection connexion;
    private Experiment      experiment;

    public Link2DriverSnapshotRecorder(LinksConnection connexion, Experiment experiment,
            MarshallingMode overrideExpIfExisting) {
        Link2DriverMarshaler.marshalling(connexion, experiment, overrideExpIfExisting);
        this.connexion = connexion;
        this.experiment = experiment;
    }

    @Override
    public void accept(Snapshot snapshot) {
        experiment.addSnapshot(snapshot);
        Link2DriverMarshaler.marshalling(connexion, experiment, MarshallingMode.ADDING_NEW_SNAPSHOTS);
        experiment.clearSnapshots();
    }

    public void closeConnection() {
        connexion.close();
    }

}
