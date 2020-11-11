package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    private  Map<Role, Short> crew;
    private  Long flightDistance;
    private  Boolean isReadyForNextMission;

    public Spaceship(String name,Map<Role, Short> crew, Long flightDistance) {
        super(name);
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMission = true;
    }


    @Override
    public String toString() {
        return "Spaceship{" +
                "name=" + getName() +
                ", crew=" + crew +
                ", flightDistance=" + flightDistance +
                ", isReadyForNextMission=" + isReadyForNextMission +
                '}';
    }


    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMission() {
        return isReadyForNextMission;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public void setReadyForNextMission(Boolean readyForNextMission) {
        isReadyForNextMission = readyForNextMission;
    }
}
