package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {
        return this.toString();
    }

    /**
     * todo via java.lang.enum methods!
     * @throws UnknownEntityException if such id does not exist
     */
    public static Role resolveRoleById(int id) {
        if (id == 1) return Role.MISSION_SPECIALIST;
        if (id == 2) return Role.FLIGHT_ENGINEER;
        if (id == 3) return Role.PILOT;
        if (id == 4) return Role.COMMANDER;
        else throw new UnknownEntityException("id does not exist");
    }
}
