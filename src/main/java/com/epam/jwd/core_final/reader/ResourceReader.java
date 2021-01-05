package com.epam.jwd.core_final.reader;

import java.util.List;

public interface ResourceReader {

    List<List<String>> read(String filePath);
}
