package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// do the same for other entities
public enum CrewMemberFactory implements EntityFactory<CrewMember> {

    INSTANCE;

    public static final Logger LOGGER = LoggerFactory.getLogger(CrewMemberFactory.class);

    @Override
    public CrewMember create(Object... args) {
        LOGGER.info("Crew member creation");
        return new CrewMember(
                (String) args[0],
                (Role) args[1],
                (Rank) args[2]);
    }
}
