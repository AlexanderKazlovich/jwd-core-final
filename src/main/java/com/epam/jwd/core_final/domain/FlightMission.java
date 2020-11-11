package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {
    // todo
    private LocalDate startDate;
    private LocalDate endDate;
    private Long missionDistance;
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMission(String missionName,
                         LocalDate startDate, LocalDate endDate,
                         Long missionDistance, Spaceship assignedSpaceShip,
                         List<CrewMember> assignedCrew, MissionResult missionResult) {
        super(missionName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.missionDistance = missionDistance;
        this.assignedSpaceShip = assignedSpaceShip;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
    }

    

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getMissionDistance() {
        return missionDistance;
    }

    public void setMissionDistance(Long missionDistance) {
        this.missionDistance = missionDistance;
    }

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public void setAssignedSpaceShip(Spaceship assignedSpaceShip) {
        this.assignedSpaceShip = assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", missionDistance=" + missionDistance +
                ", assignedSpaceShip=" + assignedSpaceShip +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                '}';
    }
}
