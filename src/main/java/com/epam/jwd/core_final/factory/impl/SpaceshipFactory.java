package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {

    // todo: MY_COMMENT: redo

    @Override
    public Spaceship create(Object... args) {
        return new Spaceship((String) args[0], (Map<Role, Short>) args[1], (Long) args[2]);
    }
}
