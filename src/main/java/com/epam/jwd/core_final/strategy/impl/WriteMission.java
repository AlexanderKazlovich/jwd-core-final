package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.strategy.WriteData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

public class WriteMission implements WriteData {
    private static WriteMission writeMission;

    private File file;
    private ApplicationProperties applicationProperties;
    {
        applicationProperties = ApplicationProperties.getApplicationProperties();
        file = new File(applicationProperties.getOutputRootDir() + applicationProperties.getMissionsFileName());
    }
    @Override
    public boolean writeFile(FlightMission flightMission) {
        try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(file,
                        NassaContext.getNassaContext().retrieveBaseEntityList(FlightMission.class));
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static WriteMission getWriteMission() {
        if (writeMission == null){
            writeMission = new WriteMission();
        }
        return writeMission;
    }
}
