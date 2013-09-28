package models.geography;

import models.AppModel;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

import static play.data.validation.Constraints.Required;

/**
 * Modèle des régions de France.
 *
 * @author Karim BENHDECH
 */
@Entity
public class Region extends AppModel {

    /**
     * Finder
     */
    private static final Model.Finder<Long, Region> FIND = new Model.Finder<Long, Region>(Long.class, Region.class);
    /**
     * Code de la région
     */
    @Required
    public String code;
    /**
     * Nom de la région
     */
    @Required
    public String name;
    /**
     * Une région contient plusieurs départements.
     */
    @OneToMany(mappedBy = "region")
    @Required
    public List<Department> departments;
    /**
     * Une région contient plusieurs villes.
     */
    @OneToMany(mappedBy = "region")
    @Required
    public List<City> cities;

    /**
     * Constructeur
     *
     * @param codeArg Code de la région
     * @param nameArg Nom de la région
     */
    public Region(final String codeArg, final String nameArg) {
        this.code = codeArg;
        this.name = nameArg;
    }

    public Region() {
    }

    /**
     * Recherche un restaurant par son identifiant
     *
     * @param regionCode Code de la région (ex: 75)
     * @return Une région
     */
    public static Region findByCode(final String regionCode) {
        return FIND
                .where()
                .eq("code", regionCode)
                .findUnique();
    }

    /**
     * La liste des régions
     *
     * @return La liste des régions
     */
    public static List<Region> findAll() {
        return FIND.where().findList();
    }

    /**
     * Représentation de l'objet.
     *
     * @return le name de la région
     */
    @Override
    public String toString() {
        return name + " (" + code + ")";
    }
}
