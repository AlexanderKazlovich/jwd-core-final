package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.*;


// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {
    Logger logger = Logger.getLogger(ApplicationMenu.class);

    ApplicationContext getApplicationContext();

    default void printAvailableOptions() {
        String options = "Press 1 : see available crew members \n" +
                "Press 2 : see available spaceships \n" +
                "Press 3 : organize flight mission \n" +
                "Press 0 : finish program execution";
        System.out.println(options);
        Scanner scanner = new Scanner(System.in);
        handleUserInput(scanner.nextInt());
    }

    default Object handleUserInput(Integer o) {
        switch (o){
            case 0 : {
                System.out.println("Bye bye :)");
                System.exit(1);
            }
            case 1 :  {
                Iterator iterator = NassaContext.getNassaContext().retrieveBaseEntityList(CrewMember.class).iterator();
                while (iterator.hasNext()){
                    System.out.println(iterator.next());
                }
                printAvailableOptions();
            }
            case 2 : {
                Iterator iterator = NassaContext.getNassaContext().retrieveBaseEntityList(Spaceship.class).iterator();
                while (iterator.hasNext()){
                    System.out.println(iterator.next());
                }
                printAvailableOptions();
            }
            case 3 : {
                userInputFlightMission();
            }
            default: {
                System.out.println("Invalid choice");
                printAvailableOptions();
            }
        }
        return null;
    }
    default Object[] userInputFlightMission(){
        Object[] objects = new Object[4];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name of mission, please");
        objects[0] = scanner.next();

        System.out.println("Enter distance of your expedition, please");
        objects[1] = scanner.nextLong();

        System.out.println("Enter name of spaceship, please");
        objects[2] = scanner.next();

        System.out.println("Enter id`s of crew member who has to take part of your expedition, please");
        List<Long> listOfId = new ArrayList<>();
        Scanner scannerId = new Scanner(System.in);

        while (scannerId.hasNextLong()){
            listOfId.add(scannerId.nextLong());
        }
        objects[3] = listOfId;
        FlightMissionFactory.getFlightMissionFactory().create(objects);

        checkForReinitialization();
        printAvailableOptions();
        return null;
    }
    default void checkForReinitialization() {
        LocalDateTime compareTime = LocalDateTime.now();
        Integer refreshRate = ApplicationProperties.getApplicationProperties().getFileRefreshRate();
        Integer difference = compareTime.getMinute() - NassaContext.initTime.getMinute();
        if (difference == refreshRate){
            try{
                NassaContext.getNassaContext().init();
                logger.info("Reinitialization is success");
            } catch (InvalidStateException e) {
                logger.error("Bad reinitialization");
            }
        }
    }

}
