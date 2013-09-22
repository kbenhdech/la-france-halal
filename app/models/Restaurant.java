package models;

import play.db.ebean.Model;

import javax.persistence.Column;
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
     * Site internet du restaurant.
     */
    public String webSite;
    /**
     * Description du restaurant.
     */
    @Column(columnDefinition = "varchar(500)")
    public String description;
    /**
     * La carte de crédit est-elle acceptée ?
     */
    @Required
    public boolean isCreditCardAccepted = false;
    /**
     * Les tickets restaurant sont-ils acceptés ?
     */
    @Required
    public boolean isRestaurantTicketAccepted = false;
    /**                                       <br />
     * La livraison à domicile est-elle disponible ?
     */
    @Required
    public boolean isDeliveryPossible = false;
    /**
     * La vente à emporter est-elle disponible ?
     */
    @Required
    public boolean isTakeaway = false;
    /**
     * La vente en ligne est-elle possible ?
     */
    @Required
    public boolean isOnlineBooking = false;
    /**
     * Il existe-il une salle de prière ?
     */
    @Required
    public boolean isPrayerRoom = false;
    /**
     * La viande est-elle certifiée Halal ?
     */
    @Required
    public boolean isEstablishmentCertified = false;
    /**
     * Il y a-t-il un aménagement handicapé ?
     */
    @Required
    public boolean isAmenagmentHandicapped = false;

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

    /**
     * Représentation de l'objet.
     *
     * @return le nom du restaurant
     */
    @Override
    public String toString() {
        return nom;
    }

}
