package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.populator.CrewPopulator;
import com.epam.jwd.core_final.populator.SpaceshipPopulator;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// todo
public class NassaContext implements ApplicationContext {

    private ApplicationProperties applicationProperties;

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<? extends BaseEntity> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.equals(CrewMember.class) && crewMembers != null) {
            return crewMembers;
        }

        if (tClass.equals(Spaceship.class) && spaceships != null) {
            return spaceships;
        }

        return Collections.emptyList();
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        applicationProperties = PropertyReaderUtil.getInstance().loadProperties();
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
        CrewPopulator crewPopulator = new CrewPopulator();
        crewMembers = crewPopulator.populateFromResources(filePath);
    }

    private void readSpaceshipResourcesFrom(String filePath) {
        SpaceshipPopulator spaceshipPopulator = new SpaceshipPopulator();
        spaceships = spaceshipPopulator.populateFromResources(filePath);
    }
}
