package links2.driver.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.mongojack.ObjectId;


public class Snapshot {

	@ObjectId
	public String _id;

    private Integer               snapshotNumber;
    private Map<String, Entity> entities;
    private Map<String, Relation> relations;
	private Map<String, Object> attributeMap;

	public Snapshot() {
        this.entities = new HashMap<>();
        this.relations = new HashMap<>();
	}

	public Set<Entity> getEntities() {
        return new HashSet<>(entities.values());
	}

	public void addEntity(Entity entity) {
        this.entities.put(entity.getEntityID(), entity);
	}

    public void setEntities(Iterable<Entity> entities) {
        this.entities = new HashMap<>();
        entities.forEach(entity -> this.entities.put(entity.getEntityID(), entity));
	}

	public Set<Relation> getRelations() {
        return new HashSet<>(relations.values());
	}

	public void addRelation(Relation relation) {
        this.relations.put(relation.getRelationID(), relation);
	}

    public void setRelations(Iterable<Relation> relations) {
        this.relations = new HashMap<>();
        relations.forEach(relation -> this.relations.put(relation.getRelationID(), relation));
	}

    public Integer getSnapshotNumber() {
		return snapshotNumber;
	}

    public void setSnapshotNumber(Integer snapshotNumber) {
		this.snapshotNumber = snapshotNumber;
	}

	public Map<String, Object> getAttributeMap() {
        return new HashMap<>(attributeMap);
	}

	public void setAttributeMap(Map<String, Object> attributeMap) {
        this.attributeMap = new HashMap<>(attributeMap);
	}

    public void setAttribute(String attributeName, String attributeValue) {
        if (null == attributeMap) {
            attributeMap = new HashMap<>();
        }
        attributeMap.put(attributeName, attributeValue);
    }

    /**
     * can return null
     */
    public Entity getEntityGivenID(String entityID) {
        return this.entities.get(entityID);
    }

    /**
     * can return null
     */
    public Relation getRelationGivenID(String relationID) {
        return this.relations.get(relationID);
    }

    public boolean hasEntityGivenID(String entityID) {
        return this.entities.containsKey(entityID);
    }

    public boolean hasRelationGivenID(String relationID) {
        return this.relations.containsKey(relationID);
    }

    public void setEntityAttribute(String entityID, String attributeName, Object attributeValue) {
        entities.computeIfPresent(entityID, (id, entity) ->{
            entity.setAttribute(attributeName, attributeValue);
            return entity;
        });
    }

    public void setRelationAttribute(String relationID, String attributeName, Object attributeValue) {
        relations.computeIfPresent(relationID, (id, relation) -> {
            relation.setAttribute(attributeName, attributeValue);
            return relation;
        });
    }

    /**
     * Relationships involving the deleted entity agent are automatically
     * removed
     */
    public void removeEntity(String entityID) {
        entities.remove(entityID);
        relations.values()
                .removeIf(relation -> relation.getNodeAid().equals(entityID) || relation.getNodeBid().equals(entityID));
    }

    public void removeRelation(String relationID) {
        relations.remove(relationID);
    }

}
