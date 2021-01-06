package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CrewMemberService implements CrewService {

    private static final ApplicationContext NASSA_CONTEXT = new NassaContext();
    private final Collection<CrewMember> crewMembers;
    private final EntityFactory<CrewMember> crewMemberFactory;

    public CrewMemberService(CrewMemberFactory crewMemberFactory) {
        crewMembers = (Collection<CrewMember>) NASSA_CONTEXT.retrieveBaseEntityList(CrewMember.class); // redo
        this.crewMemberFactory = crewMemberFactory;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return new ArrayList<>(crewMembers);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<CrewMember> criteria) {
        return null;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<CrewMember> criteria) {
        return Optional.empty();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        crewMember.setRank(Rank.CAPTAIN);
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        crewMember.setReadyForNextMissions(true); // redo
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
//        return crewMemberFactory.create(crewMember);
        return null; // how to create crewMember from crewMember
    }
}
