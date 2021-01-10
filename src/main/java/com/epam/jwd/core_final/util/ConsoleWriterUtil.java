package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.BaseEntity;

import java.util.Collection;

public final class ConsoleWriterUtil {

    private ConsoleWriterUtil() {
    }

    public static void printMsgToConsole(String msg) {
        System.out.println(msg);
    }

    public static void printCollectionToConsole(Collection<? extends BaseEntity> collection) {
        System.out.println("{");
        collection.forEach(System.out::println);
        System.out.println("}");
    }
}
