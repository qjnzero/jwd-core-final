package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<B extends BaseEntity> implements CriteriaMatcher<B> {

    private final Long id;
    private final String name;

    abstract static class Builder<T extends Builder<T>> {

        private Long id;
        private String name;

        public T withId(Long id) {
            this.id = id;
            return self();
        }

        public T withName(String name) {
            this.name = name;
            return self();
        }

        abstract Criteria build();

        protected abstract T self();
    }

    public Criteria(Builder<?> builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    @Override
    public boolean matches(B baseEntity) {
        List<Boolean> checkedCriteria = new ArrayList<>();
        if (id != null) {
            checkedCriteria.add(id.equals(baseEntity.getId()));
        }
        if (name != null) {
            checkedCriteria.add(name.equals(baseEntity.getName()));
        }
        if (checkedCriteria.isEmpty()) {
            return false;
        }
        return checkedCriteria.stream()
                .filter(b -> !b)
                .findFirst()
                .orElse(true);
    }

}
