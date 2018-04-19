package com.epam.tat.steps;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.services.AuthenticationService;
import org.jbehave.core.annotations.BeforeScenario;

public class BeforeSteps {

    @BeforeScenario
    public void setUp() {
        Browser.getInstance();
        new AuthenticationService();
    }
}
