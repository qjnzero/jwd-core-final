package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    // todo: MY_COMMENT: redo

    @Override
    public CrewMember create(Object... args) {
        return new CrewMember((String) args[0],
                Role.valueOf((String) args[1]),
                Rank.valueOf((String) args[2]));
    }
}
