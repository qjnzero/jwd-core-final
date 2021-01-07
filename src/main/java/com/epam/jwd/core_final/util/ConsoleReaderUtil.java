package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.FlightMissionServiceImpl;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.zip.InflaterInputStream;

import static com.epam.jwd.core_final.util.ConsolePrinterUtil.printMsgToConsole;

public final class ConsoleReaderUtil {

    private static final Scanner SCANNER = new Scanner(new InflaterInputStream(System.in));

    private ConsoleReaderUtil() {
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

        return SCANNER.nextLine();
    }

    public static LocalDate readDate(String msg) {
        printMsgToConsole(msg);

        int day = readInt("\nEnter day: ");
        int month = readInt("\nEnter month: ");
        int year = readInt("\nEnter year: ");
        return LocalDate.of(year, month, day);
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
}
