package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FlightMissionFactory implements EntityFactory {
    private static FlightMissionFactory flightMissionFactory;

    public FlightMission create(Object... args) {
        try{
            FlightMission flightMission = new FlightMission(
                    (String)args[0],
                    LocalDate.now(),
                    LocalDate.now().plusYears((long)((long)args[1]/100_000L)),
                    (Long)args[1],
                    SpaceshipServiceImpl.getSpaceshipServiceImpl().getSpaceshipByName((String)args[2]),
                    CrewServiceImpl.getCrewServiceImpl().getCrewMemberList((List<Long>)args[3]),
                    MissionServiceImpl.getMissionServiceImpl().getMissionResult((Long) args[1], (List<Long>)args[3], (String) args[2])
            );
            MissionServiceImpl.getMissionServiceImpl().createMission(flightMission);
        }catch (NumberFormatException | UnknownEntityException e ){
            e.printStackTrace();
        }
        return null;
    }
    public static FlightMissionFactory getFlightMissionFactory() {
        if (flightMissionFactory == null) {
            flightMissionFactory = new FlightMissionFactory();
        }
        return flightMissionFactory;
    }
}
