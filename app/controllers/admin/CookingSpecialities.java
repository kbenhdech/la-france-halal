package controllers.admin;

import models.CookingSpeciality;
import models.CookingSpeciality;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.cookingSpeciality.edit;
import views.html.admin.cookingSpeciality.list;
import views.html.admin.cookingSpeciality.show;

import java.util.List;

import static play.data.Form.form;


/**
 * Controller des spécialités culinaires.
 * 
 * @author Karim BENHDECH
 */
public class CookingSpecialities extends Controller {

    private static final Form<CookingSpeciality> COOKING_SPECIALITY_FORM = form(CookingSpeciality.class);

    /**
     * Affichage de la liste de modèles..
     *
     * @return vers la liste.
     */
    public static Result list() {
        List<CookingSpeciality> results = CookingSpeciality.findAll();
        return ok(list.render(results));
    }

    /**
     * Affichage de la page d'edit.
     *
     * @param id identifiant du modèle à éditer.
     * @return vers la page d'edit.
     */
    public static Result edit(final Long id) {
        CookingSpeciality cs = CookingSpeciality.findById(id);
        if (cs == null) {
            return noContent();
        }
        return ok(edit.render(id, COOKING_SPECIALITY_FORM.fill(cs), cs));
    }

    /**
     * Affichage de la page de création.
     *
     * @return vers la page de création.
     */
    public static Result create() {
        CookingSpeciality cs = new CookingSpeciality();
        Form<CookingSpeciality> formT = COOKING_SPECIALITY_FORM.fill(cs);
        return ok(edit.render(0L, formT, cs));
    }

    /**
     * Sauvegarde des modifications (création ou mise à jour) du modèle.
     *
     * @param id identifiant du modèle
     * @return vers la liste si ok, on reste sur la page sinon.
     */
    public static Result update(final Long id) {
        Form<CookingSpeciality> updatedForm = COOKING_SPECIALITY_FORM.bindFromRequest();
        if (updatedForm.hasErrors()) {
            CookingSpeciality cs = CookingSpeciality.findById(id);
            return badRequest(edit.render(id, updatedForm, cs));
        }
        if (id == 0L) {
            // Création
            updatedForm.get().save();
        } else {
            // Mise à jour
            CookingSpeciality model = CookingSpeciality.findById(id);
            if (model == null) {
                return noContent();
            }
            updatedForm.get().update(id);
        }
        Controller.flash("success", Messages.get("save.success"));
        return redirect(routes.CookingSpecialities.list());
    }

    /**
     * Suppression du modèle.
     *
     * @param id identifiant du modèle
     * @return vers la liste si ok, on reste sur la page sinon.
     */
    public static Result remove(final Long id) {
        CookingSpeciality model = CookingSpeciality.findById(id);
        if (model == null) {
            Controller.flash("error", "Ce modèle n'existe pas.");
            return noContent();
        }

        model.delete();
        Controller.flash("success", "Suppression effectuée.");
        return redirect(routes.CookingSpecialities.list());
    }

    /**
     * Affichage de la page de détails.
     *
     * @param id identifiant du model
     * @return vers la page de détails
     */
    public static Result show(final Long id) {
        CookingSpeciality model = CookingSpeciality.findById(id);
        if (model == null) {
            Controller.flash("error", "Ce modèle n'existe pas.");
            return noContent();
        }
        return ok(show.render(model));
    }
}
