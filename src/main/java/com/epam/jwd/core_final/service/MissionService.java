package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.List;
import java.util.Optional;

public interface MissionService {

    List<FlightMission> findAllMissions();

    List<FlightMission> findAllMissionsByCriteria(Criteria<FlightMission> criteria);

    Optional<FlightMission> findMissionByCriteria(Criteria<FlightMission> criteria);

    FlightMission updateFlightMissionDetails(FlightMission flightMission);

    FlightMission createMission(FlightMission flightMission) throws InvalidStateException;
}
