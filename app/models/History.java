package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static play.data.validation.Constraints.Required;

/**
 * Modèle de la table d'historique de l'application.
 *
 * @author Karim BENHDECH
 */
@Entity
public class History extends AppModel {

    /**
     * Finder.
     */
    private static final Finder<Long, History> FIND = new Finder<Long, History>(Long.class, History.class);
    /**
     * Nom de la table (Modèle) sur laquelle porte la modification.
     */
    @Enumerated(EnumType.STRING)
    @Required
    public TableName tableName;
    /**
     * Type de l'opération sur la table.
     */
    @Enumerated(EnumType.STRING)
    @Required
    public TableOperationType tableOperatioType;
    /**
     * Identifiant de la ligne de la table (Modèle) sur laquelle porte la modification.
     */
    @Required
    public Long lineKey;
    /**
     * Valeur de la ligne de la table (Modèle) sur laquelle porte la modification.
     * Avant modification.
     * Au format Json.
     */
    @Column(columnDefinition = "varchar(1000)")
    @Required
    public String lineValue;

    /**
     * Constructeur.
     *
     * @param tableName         le nom de la table
     * @param tableOperatioType l'opération sur la table
     * @param lineKey           l'identifiant de la ligne modifiée
     * @param lineValue         la valeur de la ligne modifiée (Json)
     */
    public History(TableName tableName, TableOperationType tableOperatioType, Long lineKey, String lineValue) {
        this.tableName = tableName;
        this.tableOperatioType = tableOperatioType;
        this.lineKey = lineKey;
        this.lineValue = lineValue;
    }

    /**
     * Nom des tables historisées.
     */
    public enum TableName {
        RESTAURANT;
    }

    /**
     * Nom des opérations de modfifications possibles sur une table.
     */
    public enum TableOperationType {
        CREATE, UPDATE, DELETE;
    }
}
