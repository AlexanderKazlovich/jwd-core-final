package com.epam.jwd.core_final;


import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.apache.log4j.Logger;


public class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            Application.start();
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
    }
}