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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// todo
public class NassaContext implements ApplicationContext {

    private static final ApplicationProperties applicationProperties;
    public static final String OUTPUT_FILE_PATH;

    static {
        applicationProperties = PropertyReaderUtil.getInstance().loadProperties();
        OUTPUT_FILE_PATH = "src/main/resources/" + applicationProperties.getOutputRootDir() + "/" +
                applicationProperties.getMissionsFileName();
    }

    // no getters/setters for them
    private static Collection<CrewMember> crewMembers = new ArrayList<>();
    private static Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<? extends BaseEntity> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.equals(CrewMember.class) && crewMembers != null) {
            return crewMembers;
        } else if (tClass.equals(Spaceship.class) && spaceships != null) {
            return spaceships;
        } else if (tClass.equals(FlightMission.class) && flightMissions != null) {
            return flightMissions;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */

    @Override
    public void init() throws InvalidStateException {
        try {
            readCrewResourcesFrom("src/main/resources/" + applicationProperties.getInputRootDir() + "/" +
                    applicationProperties.getCrewFileName());
            readSpaceshipResourcesFrom("src/main/resources/" + applicationProperties.getInputRootDir() + "/" +
                    applicationProperties.getSpaceshipsFileName());
        } catch (Exception e) {
            throw new InvalidStateException();
        }
    }

    private void readCrewResourcesFrom(String filePath) {
        Populator<CrewMember> crewPopulator = new CrewPopulator();
        crewMembers = crewPopulator.populateFromResources(filePath);
    }

    private void readSpaceshipResourcesFrom(String filePath) {
        Populator<Spaceship> spaceshipPopulator = new SpaceshipPopulator();
        spaceships = spaceshipPopulator.populateFromResources(filePath);
    }
}
