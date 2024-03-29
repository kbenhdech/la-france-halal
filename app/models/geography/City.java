package models.geography;

import com.avaje.ebean.Expr;
import models.AppModel;
import models.Restaurant;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static play.data.validation.Constraints.Required;

/**
 * Modèle des villes de France.
 *
 * @author Karim BENHDECH
 */
@Entity
public class City extends AppModel {

    /**
     * Finder
     */
    private static final Finder<Long, City> FIND = new Finder<Long, City>(Long.class, City.class);
    /**
     * Code insee de la ville.
     */
    @Required
    public String inseeCode;
    /**
     * Code postal de la ville.
     */
    public String zipCode;
    /**
     * Nom de la ville.
     */
    @Required
    public String name;
    /**
     * Région auquel appartient la ville.
     */
    @ManyToOne
    @Required
    public Region region;
    /**
     * Département auquel appartient la ville.
     */
    @ManyToOne
    @Required
    public Department department;
    /**
     * Les restaurants présents dans la ville.
     */
    @OneToMany(mappedBy = "city")
    @JsonIgnore
    public List<Restaurant> restaurants = new ArrayList<Restaurant>();

    /**
     * Constructeur
     *
     * @param inseeCodeArg Code de la ville
     * @param nameArg      Nom de la ville
     */
    public City(final String inseeCodeArg, final String zipCodeArg, final String nameArg, final Region regionArg, final Department departmentArg) {
        this.inseeCode = inseeCodeArg;
        this.zipCode = zipCodeArg;
        this.name = nameArg;
        this.region = regionArg;
        this.department = departmentArg;
    }

    /**
     * Constructeur par défaut.
     */
    public City() {
    }

    /**
     * Recherche une ville par codes.
     *
     * @param regionCode     Code insee de la région
     * @param departmentCode Code insee du département
     * @param inseeCode      Code insee de la ville
     * @return une ville
     */
    public static City findByCode(final String regionCode, final String departmentCode, final String inseeCode) {
        return FIND
                .fetch("region")
                .fetch("department")
                .where()
                .eq("region.code", regionCode)
                .eq("department.code", departmentCode)
                .eq("inseeCode", inseeCode)
                .findUnique();
    }

    /**
     * Recherche une ville par son identifiant.
     *
     * @param id identifiant
     * @return Une ville
     */
    public static City findById(final Long id) {
        return FIND
                .fetch("region")
                .fetch("department")
                .where()
                .eq("id", id)
                .findUnique();
    }

    /**
     * La liste des villes.
     *
     * @return La liste des villes
     */
    public static List<City> findAll() {
        return FIND.where().findList();
    }

    /**
     * Recherche une ville soit par code postal soit par nom.
     * Pour l'autocomplete d'un select.
     *
     * @param q terme de la recherche
     * @return une liste de villes
     */
    public static List<City> findByTerm(String q) {
        return FIND
                .where()
                .or(
                        Expr.istartsWith("zipCode", q)
                        ,
                        Expr.icontains("name", q)
                )
                .findList();
    }

    /**
     * Le nombre de villes en base.
     *
     * @return un entier
     */
    public static int count() {
        return FIND.where().findRowCount();
    }

    /**
     * Le nombre de villes en base avec un code postal.
     *
     * @return un entier
     */
    public static int countWithZipCode() {
        return FIND.where().isNotNull("zipCode").findRowCount();
    }

    /**
     * Représentation de l'objet.
     *
     * @return le name de la ville
     */
    @Override
    public String toString() {
        return name + " (" + zipCode + ")";
    }

}
