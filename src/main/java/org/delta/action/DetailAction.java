package org.delta.action;

import javax.inject.Singleton;

@Singleton
public class DetailAction implements Action {
    @Override
    public void processAction() {
        System.out.println("Detail action ...");
    }
}
