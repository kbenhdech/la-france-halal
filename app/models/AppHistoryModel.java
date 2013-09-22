package models;


import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.MappedSuperclass;
import java.io.IOException;

import static models.History.TableName.RESTAURANT;
import static models.History.TableOperationType.*;

/**
 * Modèle qui surcharge le modéle de l'application.
 * Ajout de fonctionnalités d'historique.
 *
 * @author Karim BENHDECH
 */
@MappedSuperclass
public abstract class AppHistoryModel extends AppModel {

    /**
     * Surcharge de la méthode Save.
     */
    @Override
    public void save() {
        super.save();
        super.refresh();
        new History(RESTAURANT, CREATE, this.id, this.toJson()).save();
    }

    /**
     * Surcharge de la méthode Save.
     */
    @Override
    public void save(String s) {
        super.save(s);
        super.refresh();
        new History(RESTAURANT, CREATE, this.id, this.toJson()).save();
    }

    /**
     * Surcharge de la méthode Update.
     */
    @Override
    public void update() {
        super.update();
        super.refresh();
        new History(RESTAURANT, UPDATE, this.id, this.toJson()).save();
    }

    /**
     * Surcharge de la méthode Update.
     */
    @Override
    public void update(String s) {
        super.update(s);
        super.refresh();
        new History(RESTAURANT, UPDATE, this.id, this.toJson()).save();
    }

    /**
     * Surcharge de la méthode Update.
     */
    @Override
    public void update(Object o) {
        super.update(o);
        super.refresh();
        new History(RESTAURANT, UPDATE, this.id, this.toJson()).save();
    }

    /**
     * Surcharge de la méthode Delete.
     */
    @Override
    public void delete() {
        new History(RESTAURANT, DELETE, this.id, this.toJson()).save();
        super.delete();
    }

    /**
     * Conversion d'un objet de cette classe en Json.
     *
     * @return une chaîne
     */
    private String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            return ""; //TODO Non bloquant
        }
    }
}