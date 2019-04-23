package links2.driver.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.mongojack.ObjectId;


public class Snapshot {

	@ObjectId
	public String _id;

	private Long snapshotNumber;
	private Set<Entity> entities;
	private Set<Relation> relations;
	private Map<String, Object> attributeMap;

	public Snapshot() {
		this.entities = new HashSet<>();
		this.relations = new HashSet<>();
	}

	public Set<Entity> getEntities() {
		return new HashSet<>(entities);
	}

	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}

	public void setEntities(Set<Entity> entities) {
		this.entities = new HashSet<>(entities);
	}

	public Set<Relation> getRelations() {
		return new HashSet<>(relations);
	}

	public void addRelation(Relation relation) {
		this.relations.add(relation);
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = new HashSet<>(relations);
	}

	public Long getSnapshotNumber() {
		return snapshotNumber;
	}

	public void setSnapshotNumber(Long snapshotNumber) {
		this.snapshotNumber = snapshotNumber;
	}

	public Map<String, Object> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, Object> attributeMap) {
		this.attributeMap = attributeMap;
	}

}
