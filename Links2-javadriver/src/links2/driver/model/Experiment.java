package links2.driver.model;

import java.util.List;
import java.util.Map;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Experiment {

	@ObjectId
	public String _id;

	private String experimentName;
	private Map<String, Object> attributeMap;
	
	@JsonIgnore
	private List<Snapshot> snapshots;

	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	public Map<String, Object> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, Object> attributeMap) {
		this.attributeMap = attributeMap;
	}

	public List<Snapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<Snapshot> snapshots) {
		this.snapshots = snapshots;
	}

}
