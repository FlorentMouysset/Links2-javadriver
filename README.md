# links2_javadriver

links2_javadriver is a "Links driver" for Java. See XXX for more information on Links frameworks, in particulary about standars and related projects.

links2_javadriver allows to :
*   Save a Links experiment on MongoDB
-- Save everything at the same time
-- Save as you go
*   Retreive Links experiment from MongoDB 

## Where to get it
###Source code
The source code is currently hosted on GitHub at:
https://github.com/FlorentMouysset/Links2-javadriver

### Jars
*   Direct link

TODO

*   Maven

TODO

## Example
### Save experiment (everything at the same time)
```java
import links2.driver.connection.LinksConnection;
import links2.driver.connection.LocalLinksConnection;
import links2.driver.marshaler.Link2DriverMarshaler;
import links2.driver.marshaler.MarshallingMode;
import links2.driver.model.Entity;
import links2.driver.model.Experiment;
import links2.driver.model.Relation;
import links2.driver.model.Snapshot;


//Create a new experiment
Experiment experiment = new Experiment("My experimentName");
        
//Create a new snapshot
Snapshot snapshot = new Snapshot();
snapshot.setSnapshotNumber(0);

//Create and set entities into the snapshot
Entity entity1 = new Entity("Agent 1", "Ant");
Entity entity2 = new Entity("Agent 2", "Ant");
snapshot.addEntity(entity1);
snapshot.addEntity(entity2);
        
//Create a new relation between entities 
Relation relation1_2 = new Relation("helping1", entity1, entity2, true, "Help");

// Add the relation into the snapshot
snapshot.addRelation(relation1_2);

// Add the snapshot into the experiment
experiment.addSnapshot(snapshot);

//Helper to get connection with default parameters
LinksConnection connection = LocalLinksConnection.getLocalConnexion();
        
//Save the experiment 
Link2DriverMarshaler.marshalling(connection, experiment, MarshallingMode.OVERRIDE_EXP_IF_EXISTING);

//Don't forget to close the DB connection
connection.close();
```


### Save experiment (as you go)
```java
import links2.driver.connection.LinksConnection;
import links2.driver.connection.LocalLinksConnection;
import links2.driver.marshaler.Link2DriverMarshaler;
import links2.driver.marshaler.MarshallingMode;
import links2.driver.model.Entity;
import links2.driver.model.Experiment;
import links2.driver.model.Relation;
import links2.driver.model.Snapshot;

//Helper to get connection with default parameters
LinksConnection connexion = LocalLinksConnection.getLocalConnexion();

//Create a new experiment
Experiment experiment = new Experiment("My experimentName");
        
// existing snapshots are saved and...
Link2DriverSnapshotRecorder snapshotRecorder = new Link2DriverSnapshotRecorder(connexion, experiment, MarshallingMode.OVERRIDE_EXP_IF_EXISTING);        
        
//Create a new snapshot and save it
Snapshot snapshot = getSnapshot1();
snapshotRecorder.accept(snapshot);

//make a new snapshot and save it...
snapshot = getSnapshot2();
snapshotRecorder.accept(snapshot);
//...

// To close the connection, you can use one of the two methods:
connexion.close();
//or 
snapshotRecorder.closeConnection();
```


### Retrieve experiment 
```java
import links2.driver.connection.LinksConnection;
import links2.driver.connection.LocalLinksConnection;
import links2.driver.model.Experiment;
import links2.driver.model.Snapshot;
import links2.driver.unmarshaler.Link2DriverUnmarshaler;


//Helper to get connection with default parameters
LinksConnection connection = LocalLinksConnection.getLocalConnexion();
        
//Get an existing experiment given it's name
Experiment experiment = Link2DriverUnmarshaler.unmarshalling(connection, "My experimentName");
        
//Get the list of snapshots and get the first one
List<Snapshot> snapshots = experiment.getSnapshots();
Snapshot firstSnapshot = snapshots.get(0);

//From the snapshot get the set of entities and display their ID
Set<Entity> entities = firstSnapshot.getEntities();
entities.forEach(entity -> System.out.println(entity.getEntityID()));

//From the snapshot get the set of relations and display their ID
Set<Relation> relations = firstSnapshot.getRelations();
relations.forEach(relation -> System.out.println(relation.getRelationID()));

//From any Links class (Experiment, Snapshot, Relation and Entity) you can save and retrieve custom objects.
String customValue = (String) experiment.getAttributeMap().get("MyCustomObject");

//Don't forget to close the DB connection
connection.close();
```

More examples : https://github.com/FlorentMouysset/Links2-javadriver/tree/master/Links2-javadriver/src/links2/driver/example

## Request features and report bugs
* To request a feature open a new issue, add a description of what you want and label the issue as an "enhancement".
* To report a bug, open a new issue, explain the problem as precisely as possible, label the new issue as a "bug".

Thank you :)