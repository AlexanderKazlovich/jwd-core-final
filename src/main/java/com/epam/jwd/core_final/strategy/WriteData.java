package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.FlightMission;

public interface WriteData {
    boolean writeFile(FlightMission flightMission);
}
