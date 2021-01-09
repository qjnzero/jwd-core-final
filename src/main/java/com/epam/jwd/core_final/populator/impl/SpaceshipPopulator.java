package com.epam.jwd.core_final.populator.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.populator.Populator;
import com.epam.jwd.core_final.reader.impl.SpaceshipsResourceReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum SpaceshipPopulator implements Populator<Spaceship> {

    INSTANCE;

    private static final char COMMA = ',';
    private static final char COLON = ':';

    @Override
    public Collection<Spaceship> populateFromResources(String filePath) {
        EntityFactory<Spaceship> spaceshipFactory = SpaceshipFactory.INSTANCE;
        List<Spaceship> spaceships = new ArrayList<>();
        SpaceshipsResourceReader reader = SpaceshipsResourceReader.INSTANCE;
        for (List<String> lines : reader.read(filePath)) {
            spaceships.add(spaceshipFactory.create(
                    lines.get(0),
                    mapperFromStringToMapRoleAndShort(lines.get(2)),
                    Long.parseLong(lines.get(1))
            ));
        }
        return spaceships;
    }

    private Map<Role, Short> mapperFromStringToMapRoleAndShort(String source) {
        Map<Role, Short> crew = new LinkedHashMap<>();
        source = source.substring(1, source.length() - 1);
        String[] crewPairs = source.split(String.valueOf(COMMA));
        for (String crewPair : crewPairs) {
            String[] roleAndAmount = crewPair.split(String.valueOf(COLON));
            crew.put(Role.resolveRoleById(Integer.parseInt(roleAndAmount[0])), Short.parseShort(roleAndAmount[1]));
        }
        return crew;
    }
}
