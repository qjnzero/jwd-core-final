package com.epam.jwd.core_final.populator;

import com.epam.jwd.core_final.domain.BaseEntity;

import java.util.Collection;

public interface Populator<T extends BaseEntity> {

    Collection<T> populateFromResources(String filePath);
}
