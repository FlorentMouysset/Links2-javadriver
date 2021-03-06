package links2.driver.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Entity {

    public static final String  ATTRIBUTE_NAME = "name";
	private String entityID;
	private String type;
	private Map<String, Object> attributeMap;

    public Entity(String entityID, String type) {
        this();
        this.entityID = entityID;
        this.type = type;
    }

    public Entity() {
        attributeMap = new HashMap<>();
    }

	public String getEntityID() {
		return entityID;
	}

	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, Object> getAttributeMap() {
        return new HashMap<>(attributeMap);
	}

	public void setAttributeMap(Map<String, Object> attributeMap) {
        this.attributeMap = new HashMap<>(attributeMap);
	}

    public void setAttribute(String attributeName, Object attributeValue) {
        if (null == attributeMap) {
            attributeMap = new HashMap<>();
        }
        attributeMap.put(attributeName, attributeValue);
    }

	@Override
	public String toString() {
		final int maxLen = 2;
		return "Entity [entityID=" + entityID + ", type=" + type + ", attributeMap="
				+ (attributeMap != null ? toString(attributeMap.entrySet(), maxLen) : null) + "]";
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(attributeMap, entityID, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Entity other = (Entity) obj;
		return Objects.equals(attributeMap, other.attributeMap) && Objects.equals(entityID, other.entityID)
				&& Objects.equals(type, other.type);
	}

}
