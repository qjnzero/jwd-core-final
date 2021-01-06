package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

@FunctionalInterface
public interface CriteriaMatcher<T extends BaseEntity> {

    boolean matches(T baseEntity);
}
