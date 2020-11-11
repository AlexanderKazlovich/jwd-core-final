package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private Map<Role, Short> crew;
    private  Long flightDistance;
    private  Boolean isReadyForNextMission;

    public SpaceshipCriteria(String name, Integer id, Map<Role, Short> crew, Long flightDistance, Boolean isReadyForNextMission) {
        super(name, id);
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMission = isReadyForNextMission;
    }
    public static SpaceshipCriteria.Builder builder() {
        return new SpaceshipCriteria.Builder();
    }

    public static class Builder extends Criteria.Builder<SpaceshipCriteria.Builder> {
        Map<Role, Short> crew;
        Long flightDistance;
        Boolean isReadyForNextMission;

        public SpaceshipCriteria.Builder setCrew(Map<Role, Short> crew) {
            this.crew = crew;
            return this;
        }
        public SpaceshipCriteria.Builder setFlightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }
        public SpaceshipCriteria.Builder isReadyForNextMission(Boolean isReadyForNextMission) {
            this.isReadyForNextMission = isReadyForNextMission;
            return this;
        }

        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(name, id,  crew, flightDistance, isReadyForNextMission);
        }
    }
}
