package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {
    private static CrewServiceImpl crewServiceImpl;
    @Override
    public List<CrewMember> findAllCrewMembers() {
        return NassaContext.getNassaContext().retrieveBaseEntityList(CrewMember.class).stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        return NassaContext.getNassaContext().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(crewMember -> (crewMember.getName() == criteria.getName()) &&
                                      (crewMember.getRole() == ((CrewMemberCriteria)criteria).getRole())&&
                                      (crewMember.getRank() == ((CrewMemberCriteria)criteria).getRank())&&
                                      (crewMember.getReadyForNextMission() == ((CrewMemberCriteria)criteria).getReadyForNextMission()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return NassaContext.getNassaContext().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(crewMember -> (crewMember.getName() == criteria.getName()) &&
                        (crewMember.getRole() == ((CrewMemberCriteria)criteria).getRole())&&
                        (crewMember.getRank() == ((CrewMemberCriteria)criteria).getRank())&&
                        (crewMember.getReadyForNextMission() == ((CrewMemberCriteria)criteria).getReadyForNextMission()))
                .findFirst();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        NassaContext.getNassaContext().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(member -> member.getName().equals(crewMember.getName()))
                .forEach(member -> {
                    member.setRank(crewMember.getRank());
                    member.setRole(crewMember.getRole());
                    member.setReadyForNextMission(crewMember.getReadyForNextMission());
                });
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        NassaContext.getNassaContext().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(crewMember1 -> crewMember.getName().equals(crewMember1.getName()))
                .forEach(crewMember1 -> crewMember1.setReadyForNextMission(false));
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        Long countOfDuplicates = NassaContext.getNassaContext().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(crew -> crewMember.getName().equalsIgnoreCase(crew.getName()))
                .count();
        if (countOfDuplicates == 0){
            NassaContext.getNassaContext().retrieveBaseEntityList(CrewMember.class).add(crewMember);
        }
        return crewMember;
    }

    @Override
    public List<CrewMember> getCrewMemberList(List<Long> listOfId) {
        List<CrewMember> list = NassaContext.getNassaContext().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(crewMember -> {
                    Iterator<Long> iterator = listOfId.iterator();
                    while (iterator.hasNext()){
                        if (iterator.next().equals(crewMember.getId())){
                            return true;
                        }
                    }
                    return false;
                }).collect(Collectors.toList());

        return list;
    }

    public static CrewServiceImpl getCrewServiceImpl() {
        if (crewServiceImpl == null) {
            crewServiceImpl = new CrewServiceImpl();
        }
        return crewServiceImpl;
    }
}
