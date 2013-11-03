package models;

import enums.CookingSpecialityNature;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Les spécialités culinaires.
 *
 * @author Karim BENHDECH
 */
@Entity
public class CookingSpeciality extends AppHistoryModel {

    /**
     * Finder.
     */
    private static final Model.Finder<Long, CookingSpeciality> FIND = new Model.Finder<Long, CookingSpeciality>(Long.class, CookingSpeciality.class);
    /**
     * Le nom de la spécialités culinaires.
     */
    @Constraints.Required
    public String name;
    /**
     * La nature de la spécialités culinaires.
     */
    @Constraints.Required
    public CookingSpecialityNature nature;
    /**
     * Une spécialité culinaire peut être dans plusieurs restaurants.
     */
    @ManyToMany
    @JoinTable(
            name = "restaurant_cooking_speciality",
            joinColumns = {@JoinColumn(name = "cooking_speciality_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "restaurant_id", referencedColumnName = "id")})
    public List<Restaurant> restaurants = new ArrayList<Restaurant>();

    /**
     * Recherche une spécialité par son identifiant.
     *
     * @param csId Identifiant de la spécialité.
     * @return Une spécialité.
     */
    public static CookingSpeciality findById(final Long csId) {
        return FIND
                .where()
                .eq("id", csId)
                .findUnique();
    }

    /**
     * La liste des spécialités culinaires.
     *
     * @return La liste des spécialités culinaires
     */
    public static List<CookingSpeciality> findAll() {
        return FIND.where().findList();
    }

    /**
     * La liste des spécialités culinaires.
     *
     * @param csn la nature de la spécialité culinaire
     * @return La liste des spécialités culinaires
     */
    public static List<CookingSpeciality> findAllByNature(final CookingSpecialityNature csn) {
        return FIND.where().eq("nature", csn).findList();
    }

    /**
     * Map pour un @select.
     *
     * @return toutes les spécialités de nature "COUNTRY"
     */
    public static Map<String, String> optionsByCountry() {
        return options(findAllByNature(CookingSpecialityNature.COUNTRY));
    }

    /**
     * Map pour un @select.
     *
     * @return toutes les spécialités de nature "TYPE"
     */
    public static Map<String, String> optionsByType() {
        return options(findAllByNature(CookingSpecialityNature.TYPE));
    }

    /**
     * Map pour un @select.
     *
     * @return toutes les spécialités de nature "TYPE"
     */
    public static Map<String, String> options() {
        return options(findAll());
    }

    /**
     * Map pour un @select.
     *
     * @return toutes les spécialités de nature "TYPE"
     */
    private static Map<String, String> options(final List<CookingSpeciality> css) {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (final CookingSpeciality each : css) {
            options.put(each.id.toString(), each.name);
        }
        return options;
    }
}
