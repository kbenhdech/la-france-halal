package utils;

import models.AppModel;
import models.geography.City;
import play.data.format.Formatters;
import play.db.ebean.Model;

import java.text.ParseException;
import java.util.Locale;

/**
 * Binders pour les models complexes.
 * Définir #parse() : comment le binder récupère le model à partir de l'id.
 * et #print() : equivalent du toString().
 *
 * @author Karim BENHDECH
 */
public final class DataBinders {

    /**
     * Constructeur privé.
     */
    private DataBinders() {
    }

    /**
     * Ajout des Databinders.
     */
    public static void addDataBinders() throws NoSuchFieldException {

        // Ville
        getBasicDataBinder(City.class, new Model.Finder<Long, City>(Long.class, City.class));
    }

    /**
     * Méthode utilitaire pour l'ajout d'un databinder "basic".
     *
     * @param finder finder
     * @param <T>    type
     * @throws NoSuchFieldException exception
     */
    private static <T extends AppModel> void getBasicDataBinder(final Class c, final Model.Finder<Long, T> finder) throws NoSuchFieldException {
        Formatters.register(c, new Formatters.SimpleFormatter<T>() {

            @Override
            public T parse(final String input, final Locale l) throws ParseException {
                Long id = Long.parseLong(input);
                return finder.byId(id);
            }

            @Override
            public String print(final T t, final Locale l) {
                return t.id.toString();
            }
        });
    }
}
