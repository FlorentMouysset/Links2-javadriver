package links2.driver.example;

public class Configuration {

	private String confName;
	private Enum_Profile profile;

	public Configuration(String confName, Enum_Profile profile) {
		super();
		this.confName = confName;
		this.profile = profile;
	}

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}

	public Enum_Profile getProfile() {
		return profile;
	}

	public void setProfile(Enum_Profile profile) {
		this.profile = profile;
	}

}
