package org.delta.action;

import org.delta.menu.MenuChoices;

import javax.inject.Singleton;

@Singleton
public class HelpAction implements Action {

    @Override
    public void processAction() {
        MenuChoices.help();
    }
}
