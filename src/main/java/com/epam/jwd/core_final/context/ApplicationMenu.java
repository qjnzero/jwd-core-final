package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;

import static com.epam.jwd.core_final.util.ConsolePrinterUtil.printCollectionToConsole;
import static com.epam.jwd.core_final.util.ConsolePrinterUtil.printMsgToConsole;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default String printAvailableOptions() {
        return "Menu:\n" +
                "1. Get crew members\n" +
                "2. Get spaceships\n" +
                "3. Update crew members\n" +
                "4. Update spaceships" +
                "5. Create mission\n" +
                "6. Update mission\n" +
                "7. Write to json file information about selected mission\n" +
                "8. Exit\n";
    }

    default void handleUserInput(int input) {

        printMsgToConsole(this.printAvailableOptions());

        switch (input) {
            case 1:
                printCollectionToConsole(this.getApplicationContext().retrieveBaseEntityList(CrewMember.class));
                break;
            case 2:
                printCollectionToConsole(this.getApplicationContext().retrieveBaseEntityList(Spaceship.class));
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                break;
        }

    }
}
