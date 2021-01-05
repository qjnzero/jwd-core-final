package com.epam.jwd.core_final.util.impl;

import com.epam.jwd.core_final.util.ResourceReader;

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
    public List<List<String>> read(String filePath) {
        List<List<String>> crews = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            crews = stream
                    .filter(line -> !line.startsWith("#"))
                    .map(str -> Arrays.asList(str.split(";")))
                    .flatMap(list -> list.stream().map(str -> Arrays.asList(str.split(","))))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crews;
    }
}
