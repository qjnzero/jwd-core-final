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
import java.util.stream.Collectors;

public class CrewMemberService implements CrewService {

    private static final ApplicationContext NASSA_CONTEXT = new NassaContext();
    private final Collection<CrewMember> crewMembers;
    private final EntityFactory<CrewMember> crewMemberFactory;

    public CrewMemberService(CrewMemberFactory crewMemberFactory) {
        crewMembers = (Collection<CrewMember>) NASSA_CONTEXT.retrieveBaseEntityList(CrewMember.class); // todo: MY_COMMENT: redo this method
        this.crewMemberFactory = crewMemberFactory;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return new ArrayList<>(crewMembers);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<CrewMember> criteria) {
        return crewMembers.stream().filter(criteria::matches).collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<CrewMember> criteria) {
        return crewMembers.stream().filter(criteria::matches).findFirst();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        crewMember.setRank(Rank.CAPTAIN);
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        crewMember.setReadyForNextMissions(true); // todo: MY_COMMENT: redo this method
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        return crewMemberFactory.create(crewMember.getName(), crewMember.getRole(), crewMember.getRank());
    }
}
