package com.epam.jwd.core_final.util;

import java.util.Collection;

public final class ConsolePrinterUtil {

    private ConsolePrinterUtil() {
    }

    public static void printMsgToConsole(String msg) {
        System.out.println(msg);
    }

    public static void printCollectionToConsole(Collection<?> collection) {
        System.out.println("{ ");
        for (Object o : collection) {
            System.out.println("[ " + o.toString() + " ]");
        }
        System.out.println(" }");
    }
}
