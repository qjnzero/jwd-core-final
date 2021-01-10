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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

import static com.epam.jwd.core_final.util.ConsoleReaderUtil.readDate;
import static com.epam.jwd.core_final.util.ConsoleReaderUtil.readLine;
import static com.epam.jwd.core_final.util.ConsoleReaderUtil.readLong;
import static com.epam.jwd.core_final.util.ConsoleWriterUtil.printCollectionToConsole;
import static com.epam.jwd.core_final.util.ConsoleWriterUtil.printMsgToConsole;
import static com.epam.jwd.core_final.util.JsonWriterUtil.writeToJsonFile;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    Logger LOGGER = LoggerFactory.getLogger(ApplicationMenu.class);

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

        List<CrewMember> crewMembers = DefaultCrewService.INSTANCE.findAllCrewMembers();
        List<Spaceship> spaceships = DefaultSpaceshipService.INSTANCE.findAllSpaceships();
        List<FlightMission> flightMissions = DefaultMissionService.INSTANCE.findAllMissions();
        try {
            switch (input) {
                case 1:
                    LOGGER.info("User chose to print crew members");

                    printCollectionToConsole(crewMembers);
                    printMsgToConsole("Printing finished");
                    break;
                case 2:
                    LOGGER.info("User chose to print spaceships");

                    printCollectionToConsole(spaceships);
                    printMsgToConsole("Printing finished");
                    break;
                case 3:
                    LOGGER.info("User chose to update crew member");

                    printCollectionToConsole(crewMembers);
                    String crewMemberCriteriaName = readLine("Make up a criteria to update crew member. " +
                            "Enter a name to find crew member: ");

                    CrewMemberCriteria criteriaCrewMember = new CrewMemberCriteria.Builder().withName(crewMemberCriteriaName).build();
                    DefaultCrewService.INSTANCE.updateCrewMemberDetails(
                            DefaultCrewService.INSTANCE.findCrewMemberByCriteria(criteriaCrewMember).get());
                    printMsgToConsole("Updating of crew member finished");
                    break;
                case 4:
                    LOGGER.info("User chose to update spaceship");

                    printCollectionToConsole(spaceships);
                    String spaceshipCriteriaName = readLine("Make up a criteria to update spaceship. " +
                            "Enter a name to find spaceship: ");
                    SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria.Builder().withName(spaceshipCriteriaName).build();
                    DefaultSpaceshipService.INSTANCE.updateSpaceshipDetails(
                            DefaultSpaceshipService.INSTANCE.findSpaceshipByCriteria(spaceshipCriteria).get());
                    printMsgToConsole("Updating of spaceship finished");
                    break;
                case 5:
                    LOGGER.info("User chose to create mission");

                    String flightMissionName = readLine("Enter name of the mission: ");
                    LocalDate startDate = readDate("Enter start date of the mission: ");
                    LocalDate endDate = readDate("Enter end date of the mission: ");
                    Long distance = readLong("Enter distance: ");

                    DefaultMissionService.INSTANCE.createMission(
                            FlightMissionFactory.INSTANCE.create(
                                    flightMissionName, startDate, endDate, distance));
                    printMsgToConsole("Creation of mission finished");
                    break;
                case 6:
                    LOGGER.info("User chose to update mission");

                    printCollectionToConsole(flightMissions);
                    String flightMissionCriteriaName = readLine("Make up a criteria to update flight mission. " +
                            "Enter a name to find flight mission: ");
                    FlightMissionCriteria flightMissionCriteriaToUpdate =
                            new FlightMissionCriteria.Builder().withName(flightMissionCriteriaName).build();

                    DefaultMissionService.INSTANCE.updateFlightMissionDetails(
                            DefaultMissionService.INSTANCE.findMissionByCriteria(
                                    flightMissionCriteriaToUpdate).get());
                    printMsgToConsole("Updating of mission finished");
                    break;
                case 7:
                    LOGGER.info("User chose to print mission to json file");

                    printCollectionToConsole(flightMissions);
                    String flightMissionCriteriaNameToWriteToFile = readLine("Make up a criteria to find flight mission(s)." +
                            "Enter flight mission name: ");
                    FlightMissionCriteria flightMissionCriteriaToPrint =
                            new FlightMissionCriteria.Builder().withName(flightMissionCriteriaNameToWriteToFile).build();

                    writeToJsonFile(
                            DefaultMissionService.INSTANCE.findAllMissionsByCriteria(
                                    flightMissionCriteriaToPrint), NassaContext.OUTPUT_FILE_PATH);
                    printMsgToConsole("Writing to json file finished");
                    break;
                case 8:
                    LOGGER.info("User chose to exit");

                    System.exit(0);
                    break;
                default:
                    LOGGER.info("User chose unknown operation");

                    printMsgToConsole("No such operation found");
                    break;
            }
        } catch (Throwable t) {
            LOGGER.error("An error occurred in menu");
            this.printAvailableOptions();
        }
    }
}
