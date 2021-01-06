package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private final Map<Role, Short> crew;
    private final Long flightDistance;

    public static class Builder extends Criteria.Builder<Builder> {

        private Map<Role, Short> crew;
        private Long flightDistance;

        public Builder withCrew(Map<Role, Short> crew) {
            this.crew = crew;
            return self();
        }

        public Builder withFlightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return self();
        }

        @Override
        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public SpaceshipCriteria(Builder builder) {
        super(builder);
        this.crew = builder.crew;
        this.flightDistance = builder.flightDistance;
    }
}
