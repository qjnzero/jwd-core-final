package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpaceshipFactoryTest {

    @Test
    void Create_SimpleValues_ShouldCreateSpaceship() {
        Spaceship actual = SpaceshipFactory.INSTANCE.create(
                "BestSpaceship",
                new HashMap<Role, Short>() {{
                    put(Role.MISSION_SPECIALIST, (short) 2);
                    put(Role.COMMANDER, (short) 2);
                    put(Role.PILOT, (short) 2);
                    put(Role.FLIGHT_ENGINEER, (short) 2);
                }},
                123456L);
        assertEquals("BestSpaceship", actual.getName());
        assertEquals(new HashMap<Role, Short>() {{
                         put(Role.MISSION_SPECIALIST, (short) 2);
                         put(Role.COMMANDER, (short) 2);
                         put(Role.PILOT, (short) 2);
                         put(Role.FLIGHT_ENGINEER, (short) 2);
                     }},
                actual.getCrew());
        assertEquals(123456L, actual.getFlightDistance());
    }
}