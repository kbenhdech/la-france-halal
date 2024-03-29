package controllers.admin;

import models.Restaurant;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.restaurant.edit;
import views.html.admin.restaurant.list;
import views.html.admin.restaurant.show;

import java.util.List;

import static play.data.Form.form;


/**
 * @author Karim BENHDECH
 */
public class Restaurants extends Controller {

    private static final Form<Restaurant> RESTAURANT_FORM = form(Restaurant.class);

    /**
     * Affichage de la liste de modèles..
     *
     * @return vers la liste.
     */
    public static Result list() {
        List<Restaurant> results = Restaurant.findAll();
        return ok(list.render(results));
    }

    /**
     * Affichage de la page d'edit.
     *
     * @param id identifiant du modèle à éditer.
     * @return vers la page d'edit.
     */
    public static Result edit(final Long id) {
        Restaurant restaurant = Restaurant.findById(id);
        if (restaurant == null) {
            return noContent();
        }
        return ok(edit.render(id, RESTAURANT_FORM.fill(restaurant), restaurant));
    }

    /**
     * Affichage de la page de création.
     *
     * @return vers la page de création.
     */
    public static Result create() {
        Restaurant restaurant = new Restaurant();
        Form<Restaurant> formT = RESTAURANT_FORM.fill(restaurant);
        return ok(edit.render(0L, formT, restaurant));
    }

    /**
     * Sauvegarde des modifications (création ou mise à jour) du modèle.
     *
     * @param id identifiant du modèle
     * @return vers la liste si ok, on reste sur la page sinon.
     */
    public static Result update(final Long id) {
        Form<Restaurant> updatedForm = RESTAURANT_FORM.bindFromRequest();
        if (updatedForm.hasErrors()) {
            Restaurant restaurant = Restaurant.findById(id);
            return badRequest(edit.render(id, updatedForm, restaurant));
        }
        if (id == 0L) {
            // Création
            updatedForm.get().save();
        } else {
            // Mise à jour
            Restaurant model = Restaurant.findById(id);
            if (model == null) {
                return noContent();
            }
            updatedForm.get().update(id);
        }
        Controller.flash("success", Messages.get("save.success"));
        return redirect(controllers.admin.routes.Restaurants.list());
    }

    /**
     * Suppression du modèle.
     *
     * @param id identifiant du modèle
     * @return vers la liste si ok, on reste sur la page sinon.
     */
    public static Result remove(final Long id) {
        Restaurant model = Restaurant.findById(id);
        if (model == null) {
            Controller.flash("error", "Ce modèle n'existe pas.");
            return noContent();
        }

        model.delete();
        Controller.flash("success", "Suppression effectuée.");
        return redirect(controllers.admin.routes.Restaurants.list());
    }

    /**
     * Affichage de la page de détails.
     *
     * @param id identifiant du model
     * @return vers la page de détails
     */
    public static Result show(final Long id) {
        Restaurant model = Restaurant.findById(id);
        if (model == null) {
            Controller.flash("error", "Ce modèle n'existe pas.");
            return noContent();
        }
        return ok(show.render(model));
    }
}
