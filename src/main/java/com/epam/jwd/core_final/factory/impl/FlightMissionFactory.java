package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;

public class FlightMissionFactory implements EntityFactory<FlightMission> {

    @Override
    public FlightMission create(Object... args) {
        return new FlightMission((String) args[0],
                (LocalDate) args[1],
                (LocalDate) args[2],
                (Long) args[3],
                (MissionResult) args[4]);
    }
}
