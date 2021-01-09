package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.FlightMission;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public final class JsonWriterUtil {

    private JsonWriterUtil() {
    }

    public static void writeToJsonFile(List<FlightMission> flightMissions, String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), flightMissions);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
