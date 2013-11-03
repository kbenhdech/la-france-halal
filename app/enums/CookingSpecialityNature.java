package enums;

import play.i18n.Messages;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * La nature de la spécialité.
 *
 * @author Karim BENHDECH
 */
public enum CookingSpecialityNature {
    /**
     * La spécialité culinaire est un type.
     * Ex: pizzas, pâtes, poissons, crêpes...
     */
    TYPE(Messages.get("cookingSpecialityNature.type")),
    /**
     * La spécialité culinaire provient d'un pays.
     * EX : chinois, thailandais...
     */
    COUNTRY(Messages.get("cookingSpecialityNature.country"));
    /**
     * Le nom de la nature de la spécialité culinaire.
     */
    public final String name;

    /**
     * Constructeur.
     *
     * @param name le nom de la nature de la spécialité culinaire.
     */
    private CookingSpecialityNature(final String name) {
        this.name = name;
    }

    /**
     * Map pour un @select.
     *
     * @return toutes les natures
     */
    public static Map<String, String> options() {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (final CookingSpecialityNature each : values()) {
            options.put(each.name(), each.name);
        }
        return options;
    }
}
