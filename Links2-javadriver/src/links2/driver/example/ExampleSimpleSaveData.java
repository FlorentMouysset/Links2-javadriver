package links2.driver.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import links2.driver.connection.LinksConnection;
import links2.driver.connection.LocalLinksConnection;
import links2.driver.marshaler.Link2DriverMarshaler;
import links2.driver.marshaler.MarshallingMode;
import links2.driver.model.Entity;
import links2.driver.model.Experiment;
import links2.driver.model.Relation;
import links2.driver.model.Snapshot;

public class ExampleSimpleSaveData {

	public static final String EXP_NAME = "exp_Anthill_1";
	private static final String HEALTH_ATRIBUTE = "health";
	private static final String BASE_AGENT_ID = "Agent ";


	public static void main(String[] args) {
		LinksConnection connexion = LocalLinksConnection.getLocalConnexion();

		Experiment exp = createExperiment();

        Snapshot s = createSnapshot1();
        Snapshot s2 = createSnapshot2();

		List<Snapshot> snapshots = new ArrayList<>();
		snapshots.add(s);
		snapshots.add(s2);
		exp.setSnapshots(snapshots);
		Link2DriverMarshaler.marshalling(connexion, exp, MarshallingMode.OVERRIDE_EXP_IF_EXISTING);

		connexion.close();
        System.out.println("Save ending");
	}

    private static Snapshot createSnapshot1() {
        Snapshot s = new Snapshot();
        s.setSnapshotNumber(0);
        Map<String, Entity> entities = getEntities();
        s.setEntities(new HashSet<>(entities.values()));
        Map<String, Relation> relation = getRelations();
        s.setRelations(new HashSet<>(relation.values()));
        Map<String, Object> attributeMapSnap1 = new HashMap<>();
        attributeMapSnap1.put("Encontred exception", "Unhandle NCS !");
        s.setAttributeMap(attributeMapSnap1);
        return s;
    }

    private static Snapshot createSnapshot2() {
        Snapshot s2 = createSnapshot1();
        s2.setSnapshotNumber(1);
        s2.removeEntity(BASE_AGENT_ID + 5); // "fight1" will be automatically
                                            // removed
        s2.setEntityAttribute(BASE_AGENT_ID + 1, HEALTH_ATRIBUTE, 32);
        s2.setEntityAttribute(BASE_AGENT_ID + 2, HEALTH_ATRIBUTE, 89);
        return s2;
    }

    private static Experiment createExperiment() {
        Experiment exp = new Experiment(EXP_NAME);
        Map<String, Object> attributeMapExp = new HashMap<>();
        Configuration conf = new Configuration("MyConf1", Enum_Profile.DEBUG);
        attributeMapExp.put("Configuration", conf);
        attributeMapExp.put("Description", "Anthill simulation - test for links data structure");
        attributeMapExp.put("Version", "0.1");
        exp.setAttributeMap(attributeMapExp);
        return exp;
    }

    private static Map<String, Relation> getRelations() {
        Map<String, Relation> result = new HashMap<>();

        Relation r = new Relation("fight1", BASE_AGENT_ID + 4, BASE_AGENT_ID + 5, false, "Fight");
        result.put("fight1", r);

        r = new Relation("helpping1", BASE_AGENT_ID + 2, BASE_AGENT_ID + 1, true, "Help");
        result.put("helpping1", r);

        return result;
    }

    private static Map<String, Entity> getEntities() {
        Map<String, Entity> result = new HashMap<>();
        int cptAgentId = 1;

        String id = BASE_AGENT_ID + cptAgentId++;
        Entity ant = createAnt(id, "Maholise", 30, "Furrowmaker");
        result.put(id, ant);

        id = BASE_AGENT_ID + cptAgentId++;
        ant = createAnt(id, "Tuoi", 90, "Furrowmaker");
        result.put(id, ant);

        id = BASE_AGENT_ID + cptAgentId++;
        ant = createAnt(id, "Loumwe", 50, "Shepherdess");
        result.put(id, ant);

        id = BASE_AGENT_ID + cptAgentId++;
        ant = createAnt(id, "Ereat", 15, "Warrior");
        result.put(id, ant);

        id = BASE_AGENT_ID + cptAgentId;
        Entity termite1 = createTermite(id, "Yherzok", 2, "Warrior");
        result.put(id, termite1);

        return result;
    }

    private static Entity createTermite(String entityID, String name, int health, String role) {
        return createEntity("Termite", entityID, name, health, role);
    }

    private static Entity createAnt(String entityID, String name, int health, String role) {
        return createEntity("Ant", entityID, name, health, role);
    }

    private static Entity createEntity(String type, String entityID, String name, int health, String role) {
        Entity e = new Entity(entityID, type);
        Map<String, Object> attr = new HashMap<>();
        attr.put(HEALTH_ATRIBUTE, health);
        attr.put("role", role);
        e.setAttributeMap(attr);
        e.setAttribute(Entity.ATTRIBUTE_NAME, name);
        return e;
    }

}
