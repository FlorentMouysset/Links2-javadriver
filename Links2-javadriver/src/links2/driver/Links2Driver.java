package links2.driver;

import org.mongojack.JacksonCodecRegistry;

import links2.driver.model.Experiment;
import links2.driver.model.Snapshot;

public class Links2Driver {

	protected static JacksonCodecRegistry jacksonCodecRegistry;
	static {
		jacksonCodecRegistry = JacksonCodecRegistry.withDefaultObjectMapper();
		jacksonCodecRegistry.addCodecForClass(Experiment.class);
		jacksonCodecRegistry.addCodecForClass(Snapshot.class);
	}
}
