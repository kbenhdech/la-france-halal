package models;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonIgnore;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Modèle de l'application.
 * Surcharge du Model par défaut.
 *
 * @author Karim BENHDECH
 */
@MappedSuperclass
public abstract class AppModel extends Model {

    /**
     * Identifiant.
     */
    @Id
    public Long id;
    /**
     * Date de création.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedTimestamp
    @JsonIgnore
    public Date createdAt;
    /**
     * Date de modification.
     */
    @Version
    @UpdatedTimestamp
    @JsonIgnore
    public Date updatedAt;

    /**
     * Surcharge de equals.
     *
     * @param o un objet de ce type
     * @return un boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!Objects.equal(id, ((AppModel) o).id)) {
            return false;
        }
        return true;
    }

    /**
     * Surcharge de hashcode.
     *
     * @return un entier
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

}