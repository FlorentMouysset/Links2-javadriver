package links2.driver.example;

import links2.driver.connection.LinksConnection;
import links2.driver.connection.LocalLinksConnection;
import links2.driver.model.Experiment;
import links2.driver.model.Snapshot;
import links2.driver.unmarshaler.Link2DriverUnmarshaler;

public class ExampleRetrieveData {

	public static void main(String[] args) {

		LinksConnection connexion = LocalLinksConnection.getLocalConnexion();
		
		Experiment exp = Link2DriverUnmarshaler.unmarshalling(connexion, ExampleSaveData.EXP_NAME);
		
		System.out.println(exp.getExperimentName());
		System.out.println(exp.getAttributeMap());
		System.out.println(exp.getSnapshots().size());
		exp.getSnapshots().forEach(ExampleRetrieveData::printSnapshots);

		connexion.close();
	}

	private static void printSnapshots(Snapshot snapshot) {
		System.out.println(snapshot.getSnapshotNumber());
		System.out.println(snapshot.getEntities());
		System.out.println(snapshot.getRelations());
	}


}
