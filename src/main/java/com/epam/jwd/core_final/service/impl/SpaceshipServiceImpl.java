package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {

    private static final ApplicationContext NASSA_CONTEXT = new NassaContext();
    private final Collection<Spaceship> spaceships;
    private final EntityFactory<Spaceship> spaceshipFactory;

    public SpaceshipServiceImpl(SpaceshipFactory spaceshipFactory) {
        spaceships = (Collection<Spaceship>) NASSA_CONTEXT.retrieveBaseEntityList(Spaceship.class); // todo: MY_COMMENT: redo this method
        this.spaceshipFactory = spaceshipFactory;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return new ArrayList<>(spaceships);
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<Spaceship> criteria) {
        return spaceships.stream().filter(criteria::matches).collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<Spaceship> criteria) {
        return spaceships.stream().filter(criteria::matches).findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        spaceship.setFlightDistance(1234567L);
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
        spaceship.setReadyForNextMissions(true); // todo: MY_COMMENT: redo this method
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        return spaceshipFactory.create(spaceship.getName(), spaceship.getCrew(), spaceship.getFlightDistance());
    }
}
