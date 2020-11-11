package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public final class ApplicationProperties {
    //todo
    private static final Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ApplicationProperties applicationProperties;

    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionsFileName;
    private final String spaceshipsFileName;
    private final Integer fileRefreshRate;
    private final String dateTimeFormat;

    public ApplicationProperties() {
        this.inputRootDir = PropertyReaderUtil.getProperties().getProperty("inputRootDir");
        this.outputRootDir = PropertyReaderUtil.getProperties().getProperty("outputRootDir");
        this.crewFileName =  PropertyReaderUtil.getProperties().getProperty("crewFileName");;
        this.missionsFileName =  PropertyReaderUtil.getProperties().getProperty("missionsFileName");;
        this.spaceshipsFileName =  PropertyReaderUtil.getProperties().getProperty("spaceshipsFileName");;
        this.fileRefreshRate =  Integer.parseInt(PropertyReaderUtil.getProperties().getProperty("fileRefreshRate"));
        this.dateTimeFormat =  PropertyReaderUtil.getProperties().getProperty("dateTimeFormat");
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public static ApplicationProperties getApplicationProperties(){
        if (applicationProperties == null) {
            applicationProperties = new ApplicationProperties();
        }
        return applicationProperties;
    }

}
