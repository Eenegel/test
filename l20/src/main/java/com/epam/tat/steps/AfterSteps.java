package com.epam.tat.steps;

import com.epam.tat.framework.ui.Browser;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.ScenarioType;

public class AfterSteps {

    @AfterScenario(uponType= ScenarioType.ANY)
    public void tearDown() {
        Browser.getInstance().stopBrowser();
    }
}
