package models.geography;

import models.AppModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

import static play.data.validation.Constraints.Required;

/**
 * Modèle des départements de France.
 *
 * @author Karim BENHDECH
 */
@Entity
public class Department extends AppModel {

    /**
     * Finder
     */
    private static final Finder<Long, Department> FIND = new Finder<Long, Department>(Long.class, Department.class);
    /**
     * Code du département.
     */
    @Required
    public String code;
    /**
     * Nom du département
     */
    @Required
    public String name;
    /**
     * l
     * Région auquel appartient le département.
     */
    @ManyToOne
    @Required
    public Region region;
    /**
     * Un département contient plusieurs ville.
     */
    @OneToMany(mappedBy = "department")
    @Required
    public List<City> cities;

    /**
     * Constructeur
     *
     * @param codeArg Code du département
     * @param nameArg Nom du département
     */
    public Department(final String codeArg, final String nameArg, final Region regionArg) {
        this.code = codeArg;
        this.name = nameArg;
        this.region = regionArg;
    }

    /**
     * Constructeur par défaut.
     */
    public Department() {
    }

    /**
     * Recherche un admin par son identifiant
     *
     * @param departmentCode Code du département (ex: 75)
     * @return Un département
     */
    public static Department findByCode(final String departmentCode) {
        return FIND
                .where()
                .eq("code", departmentCode)
                .findUnique();
    }

    /**
     * La liste des départements
     *
     * @return La liste des départements
     */
    public static List<Department> findAll() {
        return FIND.where().findList();
    }

    /**
     * Représentation de l'objet.
     *
     * @return le name du département
     */
    @Override
    public String toString() {
        return name + " (" + code + ")";
    }
}
