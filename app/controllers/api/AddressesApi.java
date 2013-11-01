package controllers.api;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import models.geography.City;
import play.libs.Json;
import play.mvc.Result;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

import static play.mvc.Results.*;

/**
 * APIs relatives aux adresses.
 */
public class AddressesApi {

    /**
     * Recherche une ville soit par code postal soit par nom.
     * Pour l'autocomplete d'un select.
     *
     * @param q terme de la recherche
     * @return une liste de villes au format Json
     */
    public static Result findByTerm(final String q) {
        if (q == null || q.length() < 3) {
            return badRequest("Il doit y avoir 3 caractères minimum pour la recherche.");
        }

        List<City> cities = City.findByTerm(q);
        if (cities == null || cities.size() == 0) {
            return notFound();
        }

        Collection<AdressAutocomplete> adressAutocompletes = Collections2.transform(cities, new Function<City, AdressAutocomplete>() {
            @Nullable
            @Override
            public AdressAutocomplete apply(@Nullable City city) {
                return new AdressAutocomplete(city);
            }
        });

        return ok(Json.toJson(adressAutocompletes));
    }

    /**
     * Recherche par identifiant.
     *
     * @param id identifiant
     * @return une ville
     */
    public static Result findById(final Long id) {
        if (id == null) {
            return badRequest();
        }
        City city = City.findById(id);
        if (city == null) {
            return notFound();
        }
        return ok(Json.toJson(new AdressAutocomplete(city)));
    }

    /**
     * Bean pour restreindre la quantité d'informations envoyées au client.
     */
    public static class AdressAutocomplete {
        /**
         * Identifiant de la ville.
         */
        public final Long id;
        /**
         * Nom de la ville.
         * Avec le code postal.
         */
        public final String name;

        /**
         * Constructeur.
         *
         * @param city la ville
         */
        public AdressAutocomplete(final City city) {
            this.id = city.id;

            StringBuffer nameSB = new StringBuffer(city.name);
            if (city.zipCode != null) {
                nameSB.append(" (").append(city.zipCode).append(")");
            }
            this.name = nameSB.toString();
        }
    }
}
