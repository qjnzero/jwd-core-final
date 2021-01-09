package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private final Map<Role, Short> crew;
    private final Long flightDistance;
    private final Boolean isReadyForNextMissions;

    public static class Builder extends Criteria.Builder<Builder> {

        private Map<Role, Short> crew;
        private Long flightDistance;
        private Boolean isReadyForNextMissions;

        public Builder withCrew(Map<Role, Short> crew) {
            this.crew = crew;
            return self();
        }

        public Builder withFlightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return self();
        }

        public Builder withReadyForNextMission(Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
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
        this.isReadyForNextMissions = builder.isReadyForNextMissions;
    }

    @Override
    public boolean matches(Spaceship baseEntity) {
        List<Boolean> checkedCriteria = new ArrayList<>();
        checkedCriteria.add(super.matches(baseEntity));
        if (crew != null) {
            checkedCriteria.add(crew.equals(baseEntity.getCrew()));
        }
        if (flightDistance != null) {
            checkedCriteria.add(flightDistance.equals(baseEntity.getFlightDistance()));
        }
        if (isReadyForNextMissions != null) {
            checkedCriteria.add(isReadyForNextMissions.equals(baseEntity.getReadyForNextMissions()));
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
