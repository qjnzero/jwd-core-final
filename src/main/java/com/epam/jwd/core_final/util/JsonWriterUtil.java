package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.FlightMission;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public final class JsonWriterUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonWriterUtil.class);

    private JsonWriterUtil() {
    }

    public static void writeToJsonFile(List<FlightMission> flightMissions, String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), flightMissions);
            LOGGER.info("Writing mission(s) to json file");
        } catch (Exception e) {
            LOGGER.error("Cannot write mission(s) to json file");
            e.printStackTrace();
        }
    }
}
