package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long missionDistance;
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMissionCriteria(String name,
                                 Integer id,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 Long missionDistance,
                                 Spaceship assignedSpaceShip,
                                 List<CrewMember> assignedCrew,
                                 MissionResult missionResult) {
        super(name, id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.missionDistance = missionDistance;
        this.assignedSpaceShip = assignedSpaceShip;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
    }
    public static FlightMissionCriteria.Builder builder() {
        return new FlightMissionCriteria.Builder();
    }

    public static class Builder extends Criteria.Builder<FlightMissionCriteria.Builder> {
        LocalDate startDate;
        LocalDate endDate;
        Long missionDistance;
        Spaceship assignedSpaceShip;
        List<CrewMember> assignedCrew;
        MissionResult missionResult;

        public FlightMissionCriteria.Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public FlightMissionCriteria.Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public FlightMissionCriteria.Builder setMissionDistance(Long missionDistance) {
            this.missionDistance = missionDistance;
            return this;
        }

        public FlightMissionCriteria.Builder setAssignedSpaceShip(Spaceship assignedSpaceShip) {
            this.assignedSpaceShip = assignedSpaceShip;
            return this;
        }

        public FlightMissionCriteria.Builder setAssignedCrew(List<CrewMember> assignedCrew) {
            this.assignedCrew = assignedCrew;
            return this;
        }

        public FlightMissionCriteria.Builder setMissionResult(MissionResult missionResult) {
            this.missionResult = missionResult;
            return this;
        }

        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(name, id, startDate, endDate, missionDistance, assignedSpaceShip, assignedCrew, missionResult);
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getMissionDistance() {
        return missionDistance;
    }

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }
}
