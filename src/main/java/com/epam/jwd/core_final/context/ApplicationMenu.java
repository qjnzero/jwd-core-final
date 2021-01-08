package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.util.ConsolePrinterUtil;
import com.epam.jwd.core_final.util.ConsoleReaderUtil;

import java.util.ArrayList;
import java.util.List;

import static com.epam.jwd.core_final.util.ConsoleReaderUtil.readCrewMemberCriteria;
import static com.epam.jwd.core_final.util.ConsoleReaderUtil.readFlightMission;
import static com.epam.jwd.core_final.util.ConsoleReaderUtil.readFlightMissionCriteria;
import static com.epam.jwd.core_final.util.ConsoleReaderUtil.readInt;
import static com.epam.jwd.core_final.util.ConsolePrinterUtil.printCollectionToConsole;
import static com.epam.jwd.core_final.util.ConsolePrinterUtil.printMsgToConsole;
import static com.epam.jwd.core_final.util.ConsoleReaderUtil.readSpaceshipCriteria;
import static com.epam.jwd.core_final.util.JsonWriterUtil.writeToJsonFile;
import static com.epam.jwd.core_final.util.UpdaterUtil.updateCrewMember;
import static com.epam.jwd.core_final.util.UpdaterUtil.updateFlightMission;
import static com.epam.jwd.core_final.util.UpdaterUtil.updateSpaceship;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

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

        printMsgToConsole(this.printAvailableOptions());
        List<CrewMember> crewMembers = new ArrayList(this.getApplicationContext().retrieveBaseEntityList(CrewMember.class));
        List<Spaceship> spaceships = new ArrayList(this.getApplicationContext().retrieveBaseEntityList(Spaceship.class));
        List<FlightMission> flightMissions = new ArrayList(this.getApplicationContext().retrieveBaseEntityList(FlightMission.class));
        try {
            switch (input) {
                case 1:
                    printCollectionToConsole(crewMembers); // !!!!without diamond brackets!!!!
                    break;
                case 2:
                    printCollectionToConsole(spaceships);
                    break;
                case 3:
                    printCollectionToConsole(crewMembers);
                    CrewMemberCriteria criteriaCrewMember = readCrewMemberCriteria("Make up a criteria to update crew member: ");
                    updateCrewMember(criteriaCrewMember);
                    break;
                case 4:

                    printCollectionToConsole(spaceships);
                    SpaceshipCriteria criteriaSpaceship = readSpaceshipCriteria("Make up a criteria to update spaceship: ");
                    updateSpaceship(criteriaSpaceship);
                    break;
                case 5:
                    readFlightMission("Enter mission information: ");
                    break;
                case 6:

                    printCollectionToConsole(flightMissions);
                    FlightMissionCriteria flightMissionCriteria = readFlightMissionCriteria("Make up a criteria to update flight mission: ");
                    updateFlightMission(flightMissionCriteria);
                    break;
                case 7:
                    printCollectionToConsole(flightMissions);
                    FlightMissionCriteria flightMissionCriteriaCase7 = readFlightMissionCriteria("Make up a criteria to update flight mission: ");
                    writeToJsonFile(flightMissionCriteriaCase7, NassaContext.OUTPUT_FILE_PATH);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    printMsgToConsole("No such operation found");
                    break;
            }
        } catch (Throwable t) {
            // todo: MY_COMMENT: clean console
            this.printAvailableOptions();
        }

    }
}
