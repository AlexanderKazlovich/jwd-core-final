package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.awt.*;
import java.util.ArrayList;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMission;

    CrewMemberCriteria(String name, Integer id, Role role, Rank rank, Boolean isReadyForNextMission) {
        super(name, id);
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMission = isReadyForNextMission;
    }

    public static CrewMemberCriteria.Builder builder() {
        return new CrewMemberCriteria.Builder();
    }

    public static class Builder extends Criteria.Builder<CrewMemberCriteria.Builder> {
        Role role;
        Rank rank;
        Boolean isReadyForNextMission;

        public Builder role(Role role) {
            this.role = role;
            return this;
        }
        public Builder rank(Rank rank) {
            this.rank = rank;
            return this;
        }
        public Builder isReadyForNextMission(Boolean isReadyForNextMission) {
            this.isReadyForNextMission = isReadyForNextMission;
            return this;
        }

        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(name, id, role, rank, isReadyForNextMission);
        }
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean getReadyForNextMission() {
        return isReadyForNextMission;
    }
}