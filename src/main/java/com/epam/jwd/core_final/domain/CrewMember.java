package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    // todo
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMission;
    public CrewMember(Role role, String name, Rank rank) {
        super(name);
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMission = true;
    }

    public Boolean getReadyForNextMission() {
        return isReadyForNextMission;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setReadyForNextMission(Boolean readyForNextMission) {
        isReadyForNextMission = readyForNextMission;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "id=" + getId() +
                ", role=" + role +
                ", rank=" + rank +
                ", isReadyForNextMission=" + isReadyForNextMission +
                '}';
    }
}
