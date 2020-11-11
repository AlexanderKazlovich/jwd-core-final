package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {
    private static CrewMemberFactory instance;

    @Override
    public CrewMember create(Object... args) {
        try{
            CrewMember crewMember = new CrewMember(
                    Role.resolveRoleById(Integer.parseInt((String) args[0])),
                    (String) args[1],
                    Rank.resolveRankById(Integer.parseInt((String) args[2])
                    ));
            CrewServiceImpl.getCrewServiceImpl().createCrewMember(crewMember);
        }catch (NumberFormatException | UnknownEntityException e ){
            e.printStackTrace();
        }
        return null;
    }
    public static CrewMemberFactory getCrewMemberFactory() {
        if (instance == null) {
            instance = new CrewMemberFactory();
        }
        return instance;
    }
}
