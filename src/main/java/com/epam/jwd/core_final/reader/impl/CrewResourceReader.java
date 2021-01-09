package com.epam.jwd.core_final.reader.impl;

import com.epam.jwd.core_final.reader.ResourceReader;
import com.epam.jwd.core_final.util.JsonWriterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CrewResourceReader implements ResourceReader {

    INSTANCE;

    public static final Logger LOGGER = LoggerFactory.getLogger(CrewResourceReader.class);


    private static final char SHARP = '#';
    private static final char SEMICOLON = ';';
    private static final char COMMA = ',';

    @Override
    public List<List<String>> read(String filePath) {
        List<List<String>> crews = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            crews = stream
                    .filter(line -> !line.startsWith(String.valueOf(SHARP)))
                    .map(str -> Arrays.asList(str.split(String.valueOf(SEMICOLON))))
                    .flatMap(list -> list.stream().map(str -> Arrays.asList(str.split(String.valueOf(COMMA)))))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Cannot read file");
            e.printStackTrace();
        }
        return crews;
    }
}
