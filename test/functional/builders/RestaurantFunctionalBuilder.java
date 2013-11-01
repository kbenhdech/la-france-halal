package functional.builders;

import controllers.admin.routes;
import play.i18n.Messages;
import play.test.TestBrowser;

import static functional.utils.CustomizedDriver.customizedClick;
import static functional.utils.CustomizedDriver.customizedGoto;

/**
 * Méthodes utilitaires pour les tests fonctionnels concernant le CRUD admin.
 *
 * @author Karim BENHDECH
 */
public final class RestaurantFunctionalBuilder {

    public static void create(final TestBrowser browser, final String restoName) {
        customizedGoto(browser, routes.Restaurants.create().url(), Messages.get("restaurant.h1.create"));
        browser.$("input#name").text(restoName);
        customizedClick(browser, "input[type='submit']", 0, "div.alert-success", Messages.get("save.success"));
    }

}
