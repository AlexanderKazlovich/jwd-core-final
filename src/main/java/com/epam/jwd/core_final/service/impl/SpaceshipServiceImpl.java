package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {
    private static SpaceshipServiceImpl spaceshipServiceImpl;
    @Override
    public List<Spaceship> findAllSpaceships() {
        return NassaContext.getNassaContext().retrieveBaseEntityList(Spaceship.class).stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return null;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return Optional.empty();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        NassaContext.getNassaContext().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(ship -> ship.getName().equals(spaceship.getName()))
                .forEach(ship -> {
                    ship.setCrew(spaceship.getCrew());
                    ship.setFlightDistance(spaceship.getFlightDistance());
                    ship.setReadyForNextMission(spaceship.getReadyForNextMission());
                });
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
        NassaContext.getNassaContext().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship1 -> spaceship.getName().equals(spaceship1.getName()))
                .forEach(spaceship1 -> spaceship1.setReadyForNextMission(false));
    }

    @Override
    public Spaceship createSpaceship(String[] strings) throws RuntimeException {
        Arrays.stream(strings)
                .filter(s -> (!s.isBlank()) && s.contains(":"))
                .forEach(str1 -> NassaContext.getNassaContext().retrieveBaseEntityList(Spaceship.class).add(
                        SpaceshipFactory.getSpaceshipFactory().create(
                                strings[0],
                                Arrays.stream(str1.split("[;{,}]"))
                                        .filter(s -> !s.isEmpty())
                                        .filter(i -> i.contains(":"))
                                        .map(i -> i.split(":"))
                                        .collect(Collectors.toMap(key -> Role.resolveRoleById(Integer.parseInt(key[0])), value -> Short.parseShort(value[1]))),
                                Long.parseLong(strings[1])
                        )
                        )
                );
        return null;
    }

    @Override
    public Spaceship getSpaceshipByName(String name) {
        return NassaContext.getNassaContext().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> name.equalsIgnoreCase(spaceship.getName()))
                .findFirst()
                .get();
    }

    public static SpaceshipServiceImpl getSpaceshipServiceImpl() {
        if (spaceshipServiceImpl == null) {
            spaceshipServiceImpl = new SpaceshipServiceImpl();
        }
        return spaceshipServiceImpl;
    }

}
