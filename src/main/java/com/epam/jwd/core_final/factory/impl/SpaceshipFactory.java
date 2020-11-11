package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.EntityFactory;
import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    private static SpaceshipFactory instance;

    @Override
    public Spaceship create(Object... args) {
        try{
            Spaceship spaceship = new Spaceship(
                    (String) args[0],
                    (Map) args[1],
                    (Long) args[2]
            );
            return spaceship;
        }catch (NumberFormatException | UnknownEntityException e ){
            e.printStackTrace();
        }
        return null;
    }

    public static SpaceshipFactory getSpaceshipFactory() {
        if (instance == null) {
            instance = new SpaceshipFactory();
        }
        return instance;
    }
}
