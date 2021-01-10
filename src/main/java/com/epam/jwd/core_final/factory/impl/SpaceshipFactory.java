package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public enum SpaceshipFactory implements EntityFactory<Spaceship> {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceshipFactory.class);

    @Override
    public Spaceship create(Object... args) {
        LOGGER.info("Spaceship creation");
        return new Spaceship(
                (String) args[0],
                (Map<Role, Short>) args[1],
                (Long) args[2]);
    }
}
