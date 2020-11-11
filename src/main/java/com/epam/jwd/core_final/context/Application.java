package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {

    static void start() throws InvalidStateException {
        final ApplicationMenu applicationMenu = NassaContext::getNassaContext;
        final NassaContext nassaContext =  NassaContext.getNassaContext();
        final Supplier<ApplicationContext> applicationContextSupplier = () -> nassaContext; // todo

        applicationContextSupplier.get().init();
        applicationMenu.printAvailableOptions();
    }
}
