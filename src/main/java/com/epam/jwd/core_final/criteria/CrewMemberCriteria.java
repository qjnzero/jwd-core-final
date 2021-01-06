package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    // todo: MY_COMMENT: change list to set everywhere MAYBE

    private final Role role;
    private final Rank rank;
    private final Boolean isReadyForNextMissions;

    public static class Builder extends Criteria.Builder<Builder> {

        private Role role;
        private Rank rank;
        private Boolean isReadyForNextMissions;

        public Builder withRole(Role role) {
            this.role = role;
            return self();
        }

        public Builder withRank(Rank rank) {
            this.rank = rank;
            return self();
        }

        public Builder withReadyForNextMission(Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
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
        this.isReadyForNextMissions = builder.isReadyForNextMissions;
    }

    @Override
    public boolean matches(CrewMember baseEntity) {
        List<Boolean> checkedCriteria = new ArrayList<>();
        if (role != null) {
            checkedCriteria.add(role.equals(baseEntity.getRole()));
        }
        if (rank != null) {
            checkedCriteria.add(rank.equals(baseEntity.getRank()));
        }
        if (isReadyForNextMissions != null) {
            checkedCriteria.add(isReadyForNextMissions.equals(baseEntity.getReadyForNextMissions()));
        }
        if (checkedCriteria.isEmpty()) {
            return false;
        }
        return checkedCriteria.stream().filter(b -> !b).findFirst().orElse(true);
    }
}
