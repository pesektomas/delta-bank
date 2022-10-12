package org.delta;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.delta.action.Action;
import org.delta.action.DetailAction;
import org.delta.action.HelpAction;
import org.delta.menu.MenuChoices;

public class BankInjector extends AbstractModule {

    @Override
    protected void configure() {
        //this.bind(AccountInfoPrinterService.class).to(AccountInfoPrinterService.class);

        MapBinder<MenuChoices, Action> uriBinder = MapBinder.newMapBinder(binder(), MenuChoices.class, Action.class);
        uriBinder.addBinding(MenuChoices.HELP).to(HelpAction.class);
        uriBinder.addBinding(MenuChoices.DETAIL).to(DetailAction.class);
    }
}
