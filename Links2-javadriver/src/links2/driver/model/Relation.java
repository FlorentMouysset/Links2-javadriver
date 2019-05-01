package links2.driver.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Relation {

	private String relationID;
	private String nodeAid;
	private String nodeBid;
	private Boolean isOriented;
	private String type;
    private Map<String, Object> attributeMap;


	public Relation(String relationID, Entity entity1, Entity entity2, boolean isOriented, String type) {
        this(relationID, entity1.getEntityID(), entity2.getEntityID(), isOriented, type);
	}

	public Relation(String relationID, String nodeAid, String nodeBid, boolean isOriented, String type) {
        this();
        this.relationID = relationID;
		this.nodeAid = nodeAid;
		this.nodeBid = nodeBid;
		this.isOriented = isOriented;
		this.type = type;
	}

	public Relation() {
        attributeMap = new HashMap<>();
	}

	public String getRelationID() {
		return relationID;
	}

	public void setRelationID(String relationID) {
		this.relationID = relationID;
	}

	public String getNodeAid() {
		return nodeAid;
	}

	public void setNodeAid(String nodeAid) {
		this.nodeAid = nodeAid;
	}

	public String getNodeBid() {
		return nodeBid;
	}

	public void setNodeBid(String nodeBid) {
		this.nodeBid = nodeBid;
	}

	public Boolean getIsOriented() {
		return isOriented;
	}

	public void setIsOriented(Boolean isOriented) {
		this.isOriented = isOriented;
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
        final int maxLen = 5;
        return "Relation [relationID=" + relationID + ", nodeAid=" + nodeAid + ", nodeBid=" + nodeBid + ", isOriented="
                + isOriented + ", type=" + type + ", attributeMap="
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
        return Objects.hash(attributeMap, isOriented, nodeAid, nodeBid, relationID, type);
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
        Relation other = (Relation) obj;
        return Objects.equals(attributeMap, other.attributeMap) && Objects.equals(isOriented, other.isOriented)
                && Objects.equals(nodeAid, other.nodeAid) && Objects.equals(nodeBid, other.nodeBid)
                && Objects.equals(relationID, other.relationID) && Objects.equals(type, other.type);
    }

}
