package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.context.impl.StartMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException {
        final StartMenu startMenu = new StartMenu();
        final Supplier<ApplicationContext> applicationContextSupplier = startMenu::getApplicationContext; // todo
        final NassaContext nassaContext = new NassaContext();

        nassaContext.init();
        return applicationContextSupplier::get;
    }
}
