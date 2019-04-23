package links2.driver.model;

import java.util.Objects;

public class Relation {

	private String relationID;
	private String nodeAid;
	private String nodeBid;
	private Boolean isOriented;
	private String type;

	public Relation(String relationID, Entity entity1, Entity entity2, boolean isOriented, String type) {
		this.relationID = relationID;
		this.nodeAid = entity1.getEntityID();
		this.nodeBid = entity2.getEntityID();
		this.isOriented = isOriented;
		this.type = type;
	}

	public Relation(String relationID, String nodeAid, String nodeBid, boolean isOriented, String type) {
		this.relationID = relationID;
		this.nodeAid = nodeAid;
		this.nodeBid = nodeBid;
		this.isOriented = isOriented;
		this.type = type;
	}

	public Relation() {
	}

	// private Attri
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

	@Override
	public String toString() {
		return "Relation [relationID=" + relationID + ", nodeAid=" + nodeAid + ", nodeBid=" + nodeBid + ", isOriented="
				+ isOriented + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(isOriented, nodeAid, nodeBid, relationID, type);
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
		return Objects.equals(isOriented, other.isOriented) && Objects.equals(nodeAid, other.nodeAid)
				&& Objects.equals(nodeBid, other.nodeBid) && Objects.equals(relationID, other.relationID)
				&& Objects.equals(type, other.type);
	}

}
