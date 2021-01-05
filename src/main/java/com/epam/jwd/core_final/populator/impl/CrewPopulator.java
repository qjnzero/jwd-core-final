package com.epam.jwd.core_final.populator.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.populator.Populator;
import com.epam.jwd.core_final.util.impl.CrewResourceReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CrewPopulator implements Populator<CrewMember> {

    @Override
    public Collection<CrewMember> populateFromResources(String filePath) {
        List<CrewMember> crewMembers = new ArrayList<>();
        CrewResourceReader reader = new CrewResourceReader();
        for (List<String> lines : reader.read(filePath)) {
            crewMembers.add(new CrewMember(
                    lines.get(1),
                    Role.resolveRoleById(Integer.parseInt(lines.get(0))),
                    Rank.resolveRankById(Integer.parseInt(lines.get(2))))
            );
        }
        return crewMembers;
    }
}
