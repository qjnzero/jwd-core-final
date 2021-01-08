package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public enum DefaultMissionService implements MissionService {

    INSTANCE;

    private static final ApplicationContext NASSA_CONTEXT = new NassaContext();

    @Override
    public List<FlightMission> findAllMissions() {
        return new ArrayList<>(NASSA_CONTEXT.retrieveBaseEntityList(FlightMission.class));
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<FlightMission> criteria) {
        return new ArrayList<>(NASSA_CONTEXT.retrieveBaseEntityList(FlightMission.class)).stream()
                .filter(criteria::matches)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<FlightMission> criteria) {
        return new ArrayList<>(NASSA_CONTEXT.retrieveBaseEntityList(FlightMission.class)).stream()
                .filter(criteria::matches)
                .findFirst();
    }

    @Override
    public FlightMission updateFlightMissionDetails(FlightMission flightMission) {
        Random random = new Random();
        flightMission.setMissionResult(MissionResult.resolveMissionResultById(random.nextInt(5) + 1));
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) throws InvalidStateException {
        FlightMission newFlightMission = FlightMissionFactory.INSTANCE.create(
                flightMission.getStartDate(), flightMission.getStartDate(),
                flightMission.getDistance(), flightMission.getMissionResult());
        newFlightMission.setAssignedSpaceShip(findSpaceshipToAssign(newFlightMission));
//        newFlightMission.setAssignedCrew(findCrewToAssign(newFlightMission));
        NassaContext.addEntityToStorage(newFlightMission, FlightMission.class);
        return newFlightMission;
    }

    private Spaceship findSpaceshipToAssign(FlightMission flightMission) throws InvalidStateException {
        return new ArrayList<>(NASSA_CONTEXT.retrieveBaseEntityList(Spaceship.class)).stream()
                .filter(spaceship -> spaceship.getFlightDistance() >= flightMission.getDistance())
                .findFirst()
                .orElseThrow(() -> new InvalidStateException());
    }

//    private List<CrewMember> findCrewToAssign(FlightMission flightMission) {
//
//    }
}
