package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum DefaultSpaceshipService implements SpaceshipService {

    INSTANCE;

    private final ApplicationContext NASSA_CONTEXT = new NassaContext();

    @Override
    public List<Spaceship> findAllSpaceships() {
        return new ArrayList<>((NASSA_CONTEXT.retrieveBaseEntityList(Spaceship.class)));
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<Spaceship> criteria) {
        return new ArrayList<>((NASSA_CONTEXT.retrieveBaseEntityList(Spaceship.class))).stream()
                .filter(criteria::matches)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<Spaceship> criteria) {
        return new ArrayList<>((NASSA_CONTEXT.retrieveBaseEntityList(Spaceship.class))).stream()
                .filter(criteria::matches)
                .findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        spaceship.setFlightDistance(1234567L);
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
        if (!spaceship.getReadyForNextMissions()) {
            throw new InvalidStateException("Spaceship isn't ready for the next flight mission.");
        }

        new ArrayList<>((NASSA_CONTEXT.retrieveBaseEntityList(FlightMission.class))).stream()
                .filter(mission -> mission.getMissionResult().equals(MissionResult.PLANNED))
                .findFirst()
                .orElseThrow(() -> new InvalidStateException("No available mission found"))
                .setAssignedSpaceShip(spaceship);
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        Optional<Spaceship> duplicate = new ArrayList<>((NASSA_CONTEXT.retrieveBaseEntityList(Spaceship.class))).stream()
                .filter(s -> s.equals(spaceship))
                .findAny();
        if (duplicate.isPresent()) {
            throw new InvalidStateException("Such a spaceship has already been created.");
        }

        Spaceship newSpaceship = new SpaceshipFactory().create(
                spaceship.getName(),
                spaceship.getCrew(),
                spaceship.getFlightDistance());
        NassaContext.addEntityToStorage(newSpaceship, Spaceship.class);
        return newSpaceship;
    }
}
