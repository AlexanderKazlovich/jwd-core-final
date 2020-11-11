package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.strategy.impl.WriteMission;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {
    private static MissionServiceImpl missionService;
    @Override
    public List<FlightMission> findAllMissions() {
        return NassaContext.getNassaContext().retrieveBaseEntityList(FlightMission.class).stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return NassaContext.getNassaContext().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(mission -> (mission.getName() == criteria.getName()) &&
                        (mission.getStartDate() == ((FlightMissionCriteria)criteria).getStartDate())&&
                        (mission.getEndDate() == ((FlightMissionCriteria)criteria).getEndDate())&&
                        (mission.getMissionDistance() == ((FlightMissionCriteria)criteria).getMissionDistance())&&
                        (mission.getMissionResult() == ((FlightMissionCriteria)criteria).getMissionResult())&&
                        (mission.getAssignedCrew() == ((FlightMissionCriteria)criteria).getAssignedCrew())&&
                        (mission.getAssignedSpaceShip() == ((FlightMissionCriteria)criteria).getAssignedSpaceShip()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return NassaContext.getNassaContext().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(mission -> (mission.getName() == criteria.getName()) &&
                        (mission.getStartDate() == ((FlightMissionCriteria)criteria).getStartDate())&&
                        (mission.getEndDate() == ((FlightMissionCriteria)criteria).getEndDate())&&
                        (mission.getMissionDistance() == ((FlightMissionCriteria)criteria).getMissionDistance())&&
                        (mission.getMissionResult() == ((FlightMissionCriteria)criteria).getMissionResult())&&
                        (mission.getAssignedCrew() == ((FlightMissionCriteria)criteria).getAssignedCrew())&&
                        (mission.getAssignedSpaceShip() == ((FlightMissionCriteria)criteria).getAssignedSpaceShip()))
                .findFirst();
    }

    @Override
    public FlightMission updateMissionDetails(FlightMission flightMission) {
        NassaContext.getNassaContext().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(mission -> mission.getName().equals(flightMission.getName()))
                .forEach(mission -> {
                    mission.setStartDate(flightMission.getStartDate());
                    mission.setEndDate(flightMission.getEndDate());
                    mission.setAssignedSpaceShip(flightMission.getAssignedSpaceShip());
                    mission.setAssignedCrew(flightMission.getAssignedCrew());
                    mission.setMissionResult(flightMission.getMissionResult());
                });
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        NassaContext.getNassaContext().retrieveBaseEntityList(FlightMission.class).add(flightMission);
        WriteMission.getWriteMission().writeFile(flightMission);
        return null;
    }

    @Override
    public MissionResult getMissionResult(Long distance, List<Long> list, String name) {
        Map<Role, Long> crew = CrewServiceImpl.getCrewServiceImpl().getCrewMemberList(list)
                                    .stream()
                                    .collect(Collectors.groupingBy(CrewMember::getRole,
                                                                    Collectors.counting()));
        Spaceship spaceship = SpaceshipServiceImpl.getSpaceshipServiceImpl().getSpaceshipByName(name);
        boolean compareMaps = crew.equals(spaceship.getCrew());
        return (compareMaps && (distance < spaceship.getFlightDistance()))?
                MissionResult.COMPLETED :
                MissionResult.FAILED;
    }

    public static MissionServiceImpl getMissionServiceImpl() {
        if (missionService == null) {
            missionService = new MissionServiceImpl();
        }
        return missionService;
    }
}
