package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.DefaultCrewService;
import com.epam.jwd.core_final.service.impl.DefaultMissionService;
import com.epam.jwd.core_final.service.impl.DefaultSpaceshipService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.epam.jwd.core_final.util.JsonWriterUtil.writeToJsonFile;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    Scanner SCANNER = new Scanner(System.in);

    // todo: MY_COMMENT: add logger

    ApplicationContext getApplicationContext();

    default String printAvailableOptions() {
        return "Menu:\n" +
                "1. Get crew members\n" +
                "2. Get spaceships\n" +
                "3. Update crew member\n" +
                "4. Update spaceship\n" +
                "5. Create mission\n" +
                "6. Update mission\n" +
                "7. Write to json file information about selected mission\n" +
                "8. Exit\n";
    }

    default void handleUserInput(int input) {

        System.out.println(this.printAvailableOptions());

        List<CrewMember> crewMembers = new ArrayList<>(this.getApplicationContext().retrieveBaseEntityList(CrewMember.class));
        List<Spaceship> spaceships = new ArrayList<>(this.getApplicationContext().retrieveBaseEntityList(Spaceship.class));
        List<FlightMission> flightMissions = new ArrayList<>(this.getApplicationContext().retrieveBaseEntityList(FlightMission.class));
        try {
            switch (input) {
                case 1:
                    System.out.println("{");
                    crewMembers.forEach(System.out::println);
                    System.out.println("}");
                    break;
                case 2:
                    System.out.println("{");
                    spaceships.forEach(System.out::println);
                    System.out.println("}");
                    break;
                case 3:
                    System.out.println("{");
                    crewMembers.forEach(System.out::println);
                    System.out.println("}");
                    System.out.println("Make up a criteria to update crew member: ");
                    System.out.println("Enter a name to find crew member: ");
                    String name = SCANNER.nextLine();
                    CrewMemberCriteria criteriaCrewMember = new CrewMemberCriteria.Builder().withName(name).build();
                    DefaultCrewService.INSTANCE.updateCrewMemberDetails(
                            DefaultCrewService.INSTANCE.findCrewMemberByCriteria(criteriaCrewMember).get());
                    break;
                case 4:
                    System.out.println("{");
                    spaceships.forEach(System.out::println);
                    System.out.println("}");
                    System.out.println("Make up a criteria to update spaceship: ");
                    System.out.println("Enter a name to find spaceship: ");
                    String nameS = SCANNER.nextLine();
                    SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria.Builder().withName(nameS).build();
                    DefaultSpaceshipService.INSTANCE.updateSpaceshipDetails(
                            DefaultSpaceshipService.INSTANCE.findSpaceshipByCriteria(spaceshipCriteria).get());
                    break;
                case 5:
                    System.out.println("Enter name of the mission: ");
                    String nameM = SCANNER.nextLine();
                    System.out.println("Enter start date of the mission: ");
                    int dayS = SCANNER.nextInt();
                    int monthS = SCANNER.nextInt();
                    int yearS = SCANNER.nextInt();
                    System.out.println("Enter end date of the mission: ");
                    int dayE = SCANNER.nextInt();
                    int monthE = SCANNER.nextInt();
                    int yearE = SCANNER.nextInt();
                    System.out.println("Enter distance: ");
                    Long distance = SCANNER.nextLong();
                    DefaultMissionService.INSTANCE.createMission(
                            FlightMissionFactory.INSTANCE.create(
                                    nameM,
                                    LocalDate.of(yearS, monthS, dayS),
                                    LocalDate.of(yearE, monthE, dayE),
                                    distance));
                    break;
                case 6:
                    System.out.println("Make up a criteria to find flight mission(s): ");
                    System.out.println("Enter flight mission name: ");
                    String flightMissionNameToUpdate = SCANNER.nextLine();
                    FlightMissionCriteria flightMissionCriteriaToUpdate =
                            new FlightMissionCriteria.Builder().withName(flightMissionNameToUpdate).build();

                    DefaultMissionService.INSTANCE.updateFlightMissionDetails(
                            DefaultMissionService.INSTANCE.findMissionByCriteria(
                                    flightMissionCriteriaToUpdate).get()
                    );
                    break;
                case 7:
                    System.out.println("Make up a criteria to find flight mission(s): ");
                    System.out.println("Enter flight mission name: ");
                    String flightMissionName = SCANNER.nextLine();
                    FlightMissionCriteria flightMissionCriteriaToPrint =
                            new FlightMissionCriteria.Builder().withName(flightMissionName).build();

                    writeToJsonFile(DefaultMissionService.INSTANCE.findAllMissionsByCriteria(flightMissionCriteriaToPrint),
                            NassaContext.OUTPUT_FILE_PATH);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("No such operation found");
                    break;
            }
        } catch (Throwable t) {
            // todo: MY_COMMENT: clean console
            this.printAvailableOptions();
        }
    }
}
