package gungun974.firetrack;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FireTrack implements ModInitializer {
    public static final String MOD_ID = "firetrack";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("FireTrack initialized.");
    }
}
