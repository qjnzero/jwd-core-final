package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.DuplicateEntityException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum DefaultCrewService implements CrewService {

    INSTANCE;

    private final ApplicationContext NASSA_CONTEXT = new NassaContext();

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return new ArrayList<>((NASSA_CONTEXT.retrieveBaseEntityList(CrewMember.class)));
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<CrewMember> criteria) {
        return new ArrayList<>((NASSA_CONTEXT.retrieveBaseEntityList(CrewMember.class))).stream()
                .filter(criteria::matches)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<CrewMember> criteria) {
        return new ArrayList<>((NASSA_CONTEXT.retrieveBaseEntityList(CrewMember.class))).stream()
                .filter(criteria::matches)
                .findFirst();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        crewMember.setRank(Rank.CAPTAIN);
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws InvalidStateException {
        new ArrayList<>((NASSA_CONTEXT.retrieveBaseEntityList(CrewMember.class))).stream()
                .filter(member -> member.equals(crewMember))
                .filter(CrewMember::getReadyForNextMissions)
                .findFirst()
                .orElseThrow(() -> new InvalidStateException("No available crew member was found to assign."));
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws DuplicateEntityException {
        Optional<CrewMember> duplicate = new ArrayList<>((NASSA_CONTEXT.retrieveBaseEntityList(CrewMember.class))).stream()
                .filter(c -> c.equals(crewMember))
                .findAny();
        if (duplicate.isPresent()) {
            throw new DuplicateEntityException("Such a crew member has already been created.");
        }

        CrewMember newCrewMember = CrewMemberFactory.INSTANCE.create(
                crewMember.getName(),
                crewMember.getRole(),
                crewMember.getRank());
        NassaContext.addEntityToStorage(newCrewMember, CrewMember.class);
        return newCrewMember;
    }
}
