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
        flightMission.setEndDate(flightMission.getStartDate().plusDays(13).plusMonths(13).plusYears(13));
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        FlightMission newFlightMission = FlightMissionFactory.INSTANCE.create(
                flightMission.getStartDate(), flightMission.getStartDate(),
                flightMission.getDistance(), flightMission.getMissionResult());
        NassaContext.addEntityToStorage(newFlightMission, FlightMission.class);
        return newFlightMission;
    }
}
