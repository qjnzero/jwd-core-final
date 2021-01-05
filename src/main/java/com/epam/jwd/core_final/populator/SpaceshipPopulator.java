package com.epam.jwd.core_final.populator;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.util.SpaceshipsResourceReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SpaceshipPopulator implements Populator<Spaceship> {
    @Override
    public Collection<Spaceship> populateFromResources(String filePath) {
        List<Spaceship> spaceships = new ArrayList<>();
        SpaceshipsResourceReader reader = new SpaceshipsResourceReader();
        for (List<String> lines: reader.read(filePath)) {
            spaceships.add(new Spaceship(
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
        String[] crewPairs = source.split(",");
        for (String crewPair : crewPairs) {
            String[] roleAndAmount = crewPair.split(":");
            crew.put(Role.resolveRoleById(Integer.parseInt(roleAndAmount[0])), Short.parseShort(roleAndAmount[1]));
        }
        return crew;
    }
}
