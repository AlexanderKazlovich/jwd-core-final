package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.strategy.ReadData;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReadSpaceships implements ReadData {
    private static ReadSpaceships readSpaceships;

    @Override
    public void readFile() {
        ApplicationProperties appProp = ApplicationProperties.getApplicationProperties();
        try {
            Files.lines(Paths.get(appProp.getInputRootDir() + appProp.getSpaceshipsFileName()))
                    .skip(3)
                    .map(i -> i.split("[;{}]"))
                    .forEach(strings -> SpaceshipServiceImpl.getSpaceshipServiceImpl().createSpaceship(strings));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static ReadSpaceships getReadSpaceships() {
        if (readSpaceships == null) {
            readSpaceships = new ReadSpaceships();
        }
        return readSpaceships;
    }
}
