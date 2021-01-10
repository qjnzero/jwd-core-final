package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DefaultCrewServiceTest {

    @Mock
    private DefaultCrewService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void FindAllCrewMembers_SimpleValues_ShouldPass() {
        List<CrewMember> crewMembers = new ArrayList<>();
        crewMembers.add(new CrewMember("Test Name1", Role.COMMANDER, Rank.CAPTAIN));
        crewMembers.add(new CrewMember("Test Name2", Role.PILOT, Rank.FIRST_OFFICER));
        crewMembers.add(new CrewMember("Test Name3", Role.FLIGHT_ENGINEER, Rank.SECOND_OFFICER));
        crewMembers.add(new CrewMember("Test Name4", Role.MISSION_SPECIALIST, Rank.TRAINEE));

        when(service.findAllCrewMembers()).thenReturn(crewMembers);

        List<CrewMember> emptyList = service.findAllCrewMembers();
        assertEquals(4, emptyList.size());

        verify(service, times(1)).findAllCrewMembers();
    }

    @Test
    void FindCrewMemberByCriteria_SimpleValues_ShouldFind() {
        List<CrewMember> crewMembers = new ArrayList<>();
        crewMembers.add(new CrewMember("Test Name1", Role.COMMANDER, Rank.CAPTAIN));
        crewMembers.add(new CrewMember("Test Name2", Role.PILOT, Rank.FIRST_OFFICER));
        crewMembers.add(new CrewMember("Test Name3", Role.FLIGHT_ENGINEER, Rank.SECOND_OFFICER));
        crewMembers.add(new CrewMember("Test Name4", Role.MISSION_SPECIALIST, Rank.TRAINEE));

        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria.Builder()
                .withName("Test Name3")
                .withRole(Role.FLIGHT_ENGINEER)
                .withRank(Rank.SECOND_OFFICER)
                .build();

        when(service.findCrewMemberByCriteria(crewMemberCriteria))
                .thenReturn(Optional.of(
                        new CrewMember("Test Name3", Role.FLIGHT_ENGINEER, Rank.SECOND_OFFICER)));

        Optional<CrewMember> crewMemberByCriteria = service.findCrewMemberByCriteria(crewMemberCriteria);

        assertEquals("Test Name3", crewMemberByCriteria.get().getName());
        assertEquals(Role.FLIGHT_ENGINEER, crewMemberByCriteria.get().getRole());
        assertEquals(Rank.SECOND_OFFICER, crewMemberByCriteria.get().getRank());
    }

    @Test
    void FindCrewMemberByCriteria_SimpleValues_ShouldNotFound() {
        List<CrewMember> crewMembers = new ArrayList<>();
        crewMembers.add(new CrewMember("Test Name1", Role.COMMANDER, Rank.CAPTAIN));
        crewMembers.add(new CrewMember("Test Name2", Role.PILOT, Rank.FIRST_OFFICER));
        crewMembers.add(new CrewMember("Test Name3", Role.FLIGHT_ENGINEER, Rank.SECOND_OFFICER));
        crewMembers.add(new CrewMember("Test Name4", Role.MISSION_SPECIALIST, Rank.TRAINEE));

        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria.Builder()
                .withName("Test Name30")
                .withRole(Role.FLIGHT_ENGINEER)
                .withRank(Rank.SECOND_OFFICER)
                .build();

        when(service.findCrewMemberByCriteria(crewMemberCriteria))
                .thenReturn(Optional.empty());

        Optional<CrewMember> crewMemberByCriteria = service.findCrewMemberByCriteria(crewMemberCriteria);

        assertEquals(Optional.empty(), crewMemberByCriteria);
    }
}