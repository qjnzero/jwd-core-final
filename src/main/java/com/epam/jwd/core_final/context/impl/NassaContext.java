package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.populator.Populator;
import com.epam.jwd.core_final.populator.impl.CrewPopulator;
import com.epam.jwd.core_final.populator.impl.SpaceshipPopulator;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// todo
public enum NassaContext implements ApplicationContext {

    INSTANCE;

    private static final ApplicationProperties applicationProperties;
    public static final String OUTPUT_FILE_PATH;
    public static final String PATH_PREFIX;
    public static final Character BACK_SLASH;

    static {
        PATH_PREFIX = "src/main/resources/";
        BACK_SLASH = '/';

        applicationProperties = PropertyReaderUtil.getInstance().loadProperties();
        OUTPUT_FILE_PATH = PATH_PREFIX + applicationProperties.getOutputRootDir() + BACK_SLASH +
                applicationProperties.getMissionsFileName();
    }

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<FlightMission> flightMissions = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.equals(CrewMember.class) && crewMembers != null) {
            return (Collection<T>) crewMembers;
        } else if (tClass.equals(Spaceship.class) && spaceships != null) {
            return (Collection<T>) spaceships;
        } else if (tClass.equals(FlightMission.class)) {
            return (Collection<T>) flightMissions;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */

    @Override
    public void init() throws InvalidStateException {
        try {
            readCrewResourcesFrom();
            readSpaceshipResourcesFrom();
            new File(PATH_PREFIX + applicationProperties.getOutputRootDir()).mkdir();
        } catch (Exception e) {
            throw new InvalidStateException("Cannot read from input file");
        }
    }

    private void readCrewResourcesFrom() {
        Populator<CrewMember> crewPopulator = CrewPopulator.INSTANCE;
        crewMembers = crewPopulator.populateFromResources(
                PATH_PREFIX + applicationProperties.getInputRootDir() + BACK_SLASH +
                        applicationProperties.getCrewFileName());
    }

    private void readSpaceshipResourcesFrom() {
        Populator<Spaceship> spaceshipPopulator = SpaceshipPopulator.INSTANCE;
        spaceships = spaceshipPopulator.populateFromResources(
                PATH_PREFIX + applicationProperties.getInputRootDir() + BACK_SLASH +
                        applicationProperties.getSpaceshipsFileName());
    }

    public <T extends BaseEntity> void addEntityToStorage(T baseEntity, Class<T> tClass) {
        if (tClass.equals(CrewMember.class)) {
            crewMembers.add((CrewMember) baseEntity);
        } else if (tClass.equals(Spaceship.class)) {
            spaceships.add((Spaceship) baseEntity);
        } else if (tClass.equals(FlightMission.class)) {
            flightMissions.add((FlightMission) baseEntity);
        }
    }
}
