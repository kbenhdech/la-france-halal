package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.List;

import static play.data.validation.Constraints.Required;

/**
 * Modèle du restaurant.
 *
 * @author Karim BENHDECH
 */
@Entity
public class Restaurant extends Model {

    /**
     * Finder.
     */
    private static final Model.Finder<Long, Restaurant> find = new Model.Finder<Long, Restaurant>(Long.class, Restaurant.class);

    /**
     * Identifiant.
     */
    @Id
    public Long id;

    /**
     * Nom du restaurant.
     */
    @Required
    public String nom;

    /**
     * Représentation de l'objet.
     *
     * @return
     */
    @Override
    public String toString() {
        return nom;
    }

    /**
     * Recherche un restaurant par son identifiant.
     *
     * @param restaurantId Identifiant du restaurant.
     * @return Un restaurant.
     */
    public static Restaurant findById(final Long restaurantId) {
        return find
                .where()
                .eq("id", restaurantId)
                .findUnique();
    }

    /**
     * La liste des restaurants.
     *
     * @return La liste des restaurants
     */
    public static List<Restaurant> findAll() {
        return find.where().findList();
    }

}
