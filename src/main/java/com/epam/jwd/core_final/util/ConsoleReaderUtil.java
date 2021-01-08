package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.FlightMissionServiceImpl;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.InflaterInputStream;

import static com.epam.jwd.core_final.util.ConsolePrinterUtil.printMsgToConsole;

public final class ConsoleReaderUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleReaderUtil() {
    }

    public static Short readShort(String msg) {
        printMsgToConsole(msg);

        return SCANNER.nextShort();
    }

    public static int readInt(String msg) {
        printMsgToConsole(msg);

        return SCANNER.nextInt();
    }

    public static Long readLong(String msg) {
        printMsgToConsole(msg);

        return SCANNER.nextLong();
    }

    public static String readLine(String msg) {
        printMsgToConsole(msg);
        SCANNER.next();
        return SCANNER.nextLine();
    }

    public static LocalDate readDate(String msg) {
        printMsgToConsole(msg);

        int day = readInt("\nEnter day: ");
        int month = readInt("\nEnter month: ");
        int year = readInt("\nEnter year: ");
        return LocalDate.of(year, month, day);
    }

    public static Role readRole(String msg) {
        printMsgToConsole(msg);

        System.out.println("Choose role: ");
        System.out.println("1. " + Role.MISSION_SPECIALIST);
        System.out.println("2. " + Role.FLIGHT_ENGINEER);
        System.out.println("3. " + Role.PILOT);
        System.out.println("4. " + Role.COMMANDER);

        return Role.resolveRoleById(SCANNER.nextInt());
    }

    public static Rank readRank(String msg) {
        printMsgToConsole(msg);

        System.out.println("Choose rank: ");
        System.out.println("1. " + Rank.TRAINEE);
        System.out.println("2. " + Rank.SECOND_OFFICER);
        System.out.println("3. " + Rank.FIRST_OFFICER);
        System.out.println("4. " + Rank.CAPTAIN);

        return Rank.resolveRankById(SCANNER.nextInt());
    }

    public static Boolean readReadinessForNextMission(String msg) {
        printMsgToConsole(msg);

        System.out.println("Choose readiness: ");
        System.out.println("1. Ready");
        System.out.println("2. NOT ready");
        return SCANNER.nextInt() == 1;
    }

    public static CrewMember readCrewMember(String msg) {
        printMsgToConsole(msg);

        String name = readLine("Enter name: ");
        Role role = readRole("Enter role: ");
        Rank rank = readRank("Enter rank: ");

        CrewMember crewMember = new CrewMemberFactory().create(name, role, rank);
        Boolean readiness = readReadinessForNextMission("Enter readiness for next mission: ");
        crewMember.setReadyForNextMissions(readiness);
        return crewMember;
    }

    public static List<CrewMember> readCrew(String msg) {
        printMsgToConsole(msg);

        printMsgToConsole("Enter amount of crew members: ");
        List<CrewMember> crewMembers = new ArrayList<>(SCANNER.nextInt());

        for (int i = 0; i < crewMembers.size(); i++) {
            crewMembers.add(readCrewMember("Enter crew member: "));
        }
        return crewMembers;
    }

    public static Spaceship readSpaceship(String msg) {
        printMsgToConsole(msg);

        String name = readLine("Enter name: ");
        Map<Role, Short> crew = readCrewWithRoleAndAmount("Enter crew: ");
        Long flightDistance = readLong("Enter flight distance: ");
        Boolean readiness = readReadinessForNextMission("Enter readiness for next mission: ");

        Spaceship spaceship = new SpaceshipFactory().create(name, crew, flightDistance);
        spaceship.setReadyForNextMissions(readiness);

        return spaceship;
    }

    public static MissionResult readMissionResult(String msg) {
        printMsgToConsole(msg);

        System.out.println("1. " + MissionResult.PLANNED);
        System.out.println("2. " + MissionResult.CANCELLED);
        System.out.println("3. " + MissionResult.COMPLETED);
        System.out.println("4. " + MissionResult.FAILED);
        System.out.println("5. " + MissionResult.IN_PROGRESS);
        short answer = SCANNER.nextShort();

        MissionResult missionResult;
        switch (answer) {
            case 1:
                missionResult = MissionResult.PLANNED;
                break;
            case 2:
                missionResult = MissionResult.CANCELLED;
                break;
            case 3:
                missionResult = MissionResult.COMPLETED;
                break;
            case 4:
                missionResult = MissionResult.FAILED;
                break;
            case 5:
                missionResult = MissionResult.IN_PROGRESS;
                break;
            default:
                missionResult = MissionResult.PLANNED;
                System.out.println("Default result was chosen: PLANNED");
                break;
        }
        return missionResult;
    }

    public static Map<Role, Short> readCrewWithRoleAndAmount(String msg) {
        printMsgToConsole(msg);

        Map<Role, Short> crew = new LinkedHashMap<>();
        System.out.println("Enter amount of " + Role.MISSION_SPECIALIST + ": ");
        crew.put(Role.MISSION_SPECIALIST, SCANNER.nextShort());
        System.out.println("Enter amount of " + Role.FLIGHT_ENGINEER + ": ");
        crew.put(Role.FLIGHT_ENGINEER, SCANNER.nextShort());
        System.out.println("Enter amount of " + Role.PILOT + ": ");
        crew.put(Role.PILOT, SCANNER.nextShort());
        System.out.println("Enter amount of " + Role.COMMANDER + ": ");
        crew.put(Role.COMMANDER, SCANNER.nextShort());

        return crew;
    }

    public static FlightMission readFlightMission(String msg) {
        printMsgToConsole(msg);

        String missionName = readLine("Enter mission name: ");
        return new FlightMissionFactory().create(
                missionName,
                readDate("Enter start date:"),
                readDate("Enter end date:"),
                readLong("Enter distance: "),
                null // todo: MY_COMMENT: redo mission result what value should be here
        );
    }

    public static CrewMemberCriteria readCrewMemberCriteria(String msg) {
        printMsgToConsole(msg);
        printMsgToConsole("--Enter criteria constituents or just skip--");

        Long id = readLong("Enter ID: ");
        String name = readLine("Enter name: ");
        Role role = readRole("Enter role: ");
        Rank rank = readRank("Enter rank: ");
        Boolean readiness = readReadinessForNextMission("Enter readiness for next mission: ");

        return new CrewMemberCriteria.Builder()
                .withId(id)
                .withName(name)
                .withRole(role)
                .withRank(rank)
                .withReadyForNextMission(readiness)
                .build();
    }

    public static SpaceshipCriteria readSpaceshipCriteria(String msg) {
        printMsgToConsole(msg);
        printMsgToConsole("--Enter criteria constituents or just skip--");

        Long id = readLong("Enter ID: ");
        String name = readLine("Enter name: ");
        Map<Role, Short> crew = readCrewWithRoleAndAmount("Enter crew: ");
        Long flightDistance = readLong("Enter flight distance: ");
        Boolean readiness = readReadinessForNextMission("Enter readiness for next mission: ");

        return new SpaceshipCriteria.Builder()
                .withId(id)
                .withName(name)
                .withCrew(crew)
                .withFlightDistance(flightDistance)
                .withReadyForNextMission(readiness)
                .build();
    }

    public static FlightMissionCriteria readFlightMissionCriteria(String msg) {
        printMsgToConsole(msg);
        printMsgToConsole("--Enter criteria constituents or just skip--");

        Long id = readLong("Enter ID: ");
        String name = readLine("Enter name: ");
        LocalDate startDate = readDate("Enter start date: ");
        LocalDate endDate = readDate("Enter end date: ");
        Long distance = readLong("Enter distance: ");
        List<CrewMember> assignedCrew = readCrew("Enter crew: ");
        Spaceship assignedSpaceship = readSpaceship("Enter spaceship: ");
        MissionResult missionResult = readMissionResult("Enter mission result: ");


        return new FlightMissionCriteria.Builder()
                .withId(id)
                .withName(name)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withDistance(distance)
                .withAssignedCrew(assignedCrew)
                .withAssignedSpaceship(assignedSpaceship)
                .withMissionResult(missionResult)
                .build();

    }
}
