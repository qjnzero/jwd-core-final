package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.CrewMemberService;
import com.epam.jwd.core_final.service.impl.FlightMissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import javax.swing.text.html.Option;
import java.util.Optional;

public final class UpdaterUtil {
    private UpdaterUtil() {
    }

    public static void updateCrewMember(CrewMemberCriteria crewMemberCriteria) {
        CrewMemberService crewMemberService = new CrewMemberService(new CrewMemberFactory());
        Optional<CrewMember> crewMember = crewMemberService.findCrewMemberByCriteria(crewMemberCriteria);
        if (!crewMember.isPresent()) {
//            throw new UnknownEntityException(); // todo: MY_COMMENT: redo this method
        }
            crewMemberService.updateCrewMemberDetails(crewMember.get());
    }

    public static void updateSpaceship(SpaceshipCriteria spaceshipCriteria) {
        SpaceshipServiceImpl spaceshipService = new SpaceshipServiceImpl(new SpaceshipFactory());
        Optional<Spaceship> spaceship = spaceshipService.findSpaceshipByCriteria(spaceshipCriteria);
        if (!spaceship.isPresent()) {
//            throw new UnknownEntityException(); // todo: MY_COMMENT: redo this method
        }
        spaceshipService.updateSpaceshipDetails(spaceship.get());
    }

    public static void updateFlightMission(FlightMissionCriteria flightMissionCriteria) {
        FlightMissionServiceImpl flightMissionService = new FlightMissionServiceImpl(new FlightMissionFactory());
        Optional<FlightMission> flightMission = flightMissionService.findMissionByCriteria(flightMissionCriteria);
        if (!flightMission.isPresent()) {
//            throw new UnknownEntityException(); // todo: MY_COMMENT: redo this method
        }
        flightMissionService.updateSpaceshipDetails(flightMission.get());
    }

}
