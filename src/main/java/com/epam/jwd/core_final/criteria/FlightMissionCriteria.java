package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {

    // todo: MY_COMMENT: redo

    public static class Builder extends Criteria.Builder<Builder> {

        private Role role;
        private Rank rank;

        public Builder withRole(Role role) {
            this.role = role;
            return self();
        }

        public Builder withRank(Rank rank) {
            this.rank = rank;
            return self();
        }

        @Override
        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public FlightMissionCriteria(Builder builder) {
        super(builder);
    }
}
