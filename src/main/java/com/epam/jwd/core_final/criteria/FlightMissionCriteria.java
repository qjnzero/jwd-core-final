package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Long distance;
    private final Spaceship assignedSpaceship;
    private final List<CrewMember> assignedCrew;
    private final MissionResult missionResult;

    public static class Builder extends Criteria.Builder<Builder> {

        private LocalDate startDate;
        private LocalDate endDate;
        private Long distance;
        private Spaceship assignedSpaceship;
        private List<CrewMember> assignedCrew;
        private MissionResult missionResult;

        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return self();
        }

        public Builder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return self();
        }

        public Builder withDistance(Long distance) {
            this.distance = distance;
            return self();
        }

        public Builder withAssignedSpaceship(Spaceship assignedSpaceship) {
            this.assignedSpaceship = assignedSpaceship;
            return self();
        }

        public Builder withAssignedCrew(List<CrewMember> assignedCrew) {
            this.assignedCrew = assignedCrew;
            return self();
        }

        public Builder withMissionResult(MissionResult missionResult) {
            this.missionResult = missionResult;
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
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.distance = builder.distance;
        this.assignedSpaceship = builder.assignedSpaceship;
        this.assignedCrew = builder.assignedCrew;
        this.missionResult = builder.missionResult;
    }

    @Override
    public boolean matches(FlightMission baseEntity) {
        List<Boolean> checkedCriteria = new ArrayList<>();
        checkedCriteria.add(super.matches(baseEntity));
        if (startDate != null) {
            checkedCriteria.add(startDate.equals(baseEntity.getStartDate()));
        }
        if (endDate != null) {
            checkedCriteria.add(endDate.equals(baseEntity.getEndDate()));
        }
        if (distance != null) {
            checkedCriteria.add(distance.equals(baseEntity.getDistance()));
        }
        if (assignedSpaceship != null) {
            checkedCriteria.add(assignedSpaceship.equals(baseEntity.getAssignedSpaceShip()));
        }
        if (assignedCrew != null) {
            checkedCriteria.add(assignedCrew.equals(baseEntity.getAssignedCrew()));
        }
        if (missionResult != null) {
            checkedCriteria.add(missionResult.equals(baseEntity.getMissionResult()));
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
