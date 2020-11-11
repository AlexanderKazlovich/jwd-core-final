package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.strategy.impl.ReadCrew;
import com.epam.jwd.core_final.strategy.impl.ReadSpaceships;
import org.apache.log4j.Logger;
import java.time.LocalDateTime;
import java.util.*;


// todo
public class NassaContext implements ApplicationContext {
    private static Logger logger = Logger.getLogger(Main.class);
    private static NassaContext nassaContext;
    public static LocalDateTime initTime;

    private NassaContext(){}
    // no getters/setters for them
    private final Collection<CrewMember> crewMembers = new ArrayList<>();
    private final Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<FlightMission> flightMission = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        return CrewMember.class.getName().equals(tClass.getName()) ? (Collection<T>) crewMembers :
                Spaceship.class.getName().equals(tClass.getName()) ? (Collection<T>) spaceships :
                 FlightMission.class.getName().equals(tClass.getName()) ? (Collection<T>) flightMission : null;

    }
    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException{
        initTime = LocalDateTime.now();
        ReadCrew.getReadCrew().readFile();
        ReadSpaceships.getReadSpaceships().readFile();
        logger.info("Initialization");
    }

    public static NassaContext getNassaContext() {
        if (nassaContext == null) {
            nassaContext = new NassaContext();
        }
        return nassaContext;
    }
}
