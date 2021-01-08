package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultMissionService implements MissionService {

    private static final ApplicationContext NASSA_CONTEXT = new NassaContext();
    private final Collection<FlightMission> flightMissions;
    private final EntityFactory<FlightMission> flightMissionEntityFactory;

    public DefaultMissionService(FlightMissionFactory flightMissionEntityFactory) {
        this.flightMissions = (Collection<FlightMission>) NASSA_CONTEXT.retrieveBaseEntityList(FlightMission.class);  // todo: MY_COMMENT: redo this method
        this.flightMissionEntityFactory = flightMissionEntityFactory;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return new ArrayList<>(flightMissions);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<FlightMission> criteria) {
        return flightMissions.stream().filter(criteria::matches).collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<FlightMission> criteria) {
        return flightMissions.stream().filter(criteria::matches).findFirst();
    }

    @Override
    public FlightMission updateFlightMissionDetails(FlightMission flightMission) {
        flightMission.setEndDate(flightMission.getStartDate().plusDays(12).plusMonths(1).plusYears(10));
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        FlightMission newFlightMission = flightMissionEntityFactory.create(
                flightMission.getStartDate(), flightMission.getStartDate(), // todo: MY_COMMENT: redo this method
                flightMission.getDistance(), flightMission.getAssignedSpaceShip(),
                flightMission.getAssignedCrew(), flightMission.getMissionResult());
        flightMissions.add(newFlightMission);
        NassaContext.addEntityToStorage(newFlightMission, FlightMission.class);
        return newFlightMission;
    }
}
