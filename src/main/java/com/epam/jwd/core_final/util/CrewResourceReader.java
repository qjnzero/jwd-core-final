package com.epam.jwd.core_final.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrewResourceReader implements ResourceReader {

    @Override
    public List<String> read(String filePath) {
        List<String> crews = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            crews = stream
                    .filter(line -> !line.startsWith("#"))
                    .map(str -> Arrays.asList(str.split(";")))
                    .collect(Collectors.toList())
                    .get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return crews;
    }
}
