package links2.driver;

import org.mongojack.JacksonCodecRegistry;

import links2.driver.model.Experiment;
import links2.driver.model.Snapshot;

/**
 * This class should not be used by the driver users.
 */
public class Links2Driver {
    protected static JacksonCodecRegistry jacksonCodecRegistry;

	static {
        // add here the new document type
		jacksonCodecRegistry = JacksonCodecRegistry.withDefaultObjectMapper();
		jacksonCodecRegistry.addCodecForClass(Experiment.class);
		jacksonCodecRegistry.addCodecForClass(Snapshot.class);
	}

}
