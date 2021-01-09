package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public enum FlightMissionFactory implements EntityFactory<FlightMission> {

    INSTANCE;

    public static final Logger LOGGER = LoggerFactory.getLogger(FlightMissionFactory.class);

    @Override
    public FlightMission create(Object... args) {
        LOGGER.info("Flight mission creation");
        return new FlightMission(
                (String) args[0],
                (LocalDate) args[1],
                (LocalDate) args[2],
                (Long) args[3]);
    }
}
