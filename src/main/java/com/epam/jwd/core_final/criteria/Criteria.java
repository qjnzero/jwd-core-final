package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<B extends BaseEntity> {

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
}
