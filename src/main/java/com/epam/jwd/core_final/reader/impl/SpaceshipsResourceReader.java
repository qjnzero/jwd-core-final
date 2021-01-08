package com.epam.jwd.core_final.reader.impl;

import com.epam.jwd.core_final.reader.ResourceReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SpaceshipsResourceReader implements ResourceReader {

    INSTANCE;

    private static final char SHARP = '#';
    private static final char SEMICOLON = ';';

    @Override
    public List<List<String>> read(String filePath) {
        List<List<String>> spaceships = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            spaceships = stream
                    .filter(line -> !line.startsWith(String.valueOf(SHARP)))
                    .map(str -> Arrays.asList(str.split(String.valueOf(SEMICOLON))))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return spaceships;
    }
}
