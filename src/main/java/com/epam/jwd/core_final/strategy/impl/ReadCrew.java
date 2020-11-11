package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.strategy.ReadData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ReadCrew implements ReadData {
    private static ReadCrew readCrew;
    @Override
    public void readFile() {
        ApplicationProperties appProp = ApplicationProperties.getApplicationProperties();
        try {
            Files.lines(Paths.get(appProp.getInputRootDir() + appProp.getCrewFileName()))
                    .skip(1)
                    .findFirst()
                    .map(i -> Arrays.stream(i.split(";")))
                    .get()
                    .forEach(str -> CrewMemberFactory.getCrewMemberFactory().create((Object[]) str.split(",")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static ReadCrew getReadCrew() {
        if (readCrew == null) {
            readCrew = new ReadCrew();
        }
        return readCrew;
    }
}
