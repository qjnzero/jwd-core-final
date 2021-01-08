package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.DefaultMissionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Optional;

public final class JsonWriterUtil {

    private JsonWriterUtil() {
    }

    public static void writeToJsonFile(FlightMissionCriteria flightMissionCriteria, String filePath) {
        DefaultMissionService flightMissionService = new DefaultMissionService(new FlightMissionFactory());
        Optional<FlightMission> flightMission = flightMissionService.findMissionByCriteria(flightMissionCriteria);
        if (!flightMission.isPresent()) {
//            throw new UnknownEntityException(); // todo: MY_COMMENT: redo this method
        }
        flightMissionService.updateFlightMissionDetails(flightMission.get());

        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(new File(filePath), flightMission);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
