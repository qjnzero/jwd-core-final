package com.epam.jwd.core_final.util;

import java.time.LocalDate;
import java.util.Scanner;

import static com.epam.jwd.core_final.util.ConsoleWriterUtil.printMsgToConsole;

public final class ConsoleReaderUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleReaderUtil() {
    }

    public static String readLine(String msg) {
        printMsgToConsole(msg);

        return SCANNER.nextLine();
    }

    public static LocalDate readDate(String msg) {
        printMsgToConsole(msg);

        printMsgToConsole("Enter day: ");
        int day = SCANNER.nextInt();
        printMsgToConsole("Enter month: ");
        int month = SCANNER.nextInt();
        printMsgToConsole("Enter year: ");
        int year = SCANNER.nextInt();

        return LocalDate.of(year, month, day);
    }

    public static Long readLong(String msg) {
        printMsgToConsole(msg);

        return SCANNER.nextLong();
    }
}
