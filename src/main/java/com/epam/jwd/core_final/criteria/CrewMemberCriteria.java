package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    private final Role role;
    private final Rank rank;

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
        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public CrewMemberCriteria(Builder builder) {
        super(builder);
        this.role = builder.role;
        this.rank = builder.rank;
    }
}
