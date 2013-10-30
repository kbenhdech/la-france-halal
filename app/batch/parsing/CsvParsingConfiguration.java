package batch.parsing;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.prefs.CsvPreference;

/**
 * Configuration pour le parsing CSV.
 */
public class CsvParsingConfiguration {
    /**
     * Champ de l'objet généré correspondant à chaque colonne.
     */
    private String[] headers;
    /**
     * Processors pour chaque chaque cellule.
     */
    private CellProcessor[] processors;
    /**
     * Type d'objet généré par le parseur.
     */
    private Class parsedType;
    /**
     * Indique si le fichier contient une entête, pour ne pas la prendre en compte.
     * Attention, fournir néanmoins le name des colonnes dans 'headers'.
     */
    private boolean hasHeader;
    /**
     * Préférences du parser :
     * - Délimiteur de texte,
     * - Séparateur de champ,
     * - Saut de ligne.
     */
    private CsvPreference preference;

    /**
     * Constructeur par défaut (utilisé pour les tests).
     */
    protected CsvParsingConfiguration() {
    }

    /**
     * Constructeur.
     *
     * @param parsedType    type d'objet généré par le parseur
     * @param headers       champs correspondants aux colonnes
     * @param processors    CellProcessors correspondants aux colonnes
     * @param hasHeader     le fichier contient une entête, pour ne pas la prendre en compte.
     * @param csvPreference Préférence CSV
     */
    public CsvParsingConfiguration(final Class parsedType, final String[] headers, final CellProcessor[] processors, final boolean hasHeader, final CsvPreference csvPreference) {
        this.headers = headers.clone();
        this.processors = processors.clone();
        this.parsedType = parsedType;
        this.hasHeader = hasHeader;
        this.preference = csvPreference;
    }

    /**
     * Constructeur pour les fichiers sans entête.
     *
     * @param parsedType type d'objet généré par le parseur
     * @param headers    champs correspondants aux colonnes
     * @param processors CellProcessors correspondants aux colonnes
     */
    public CsvParsingConfiguration(final Class parsedType, final String[] headers, final CellProcessor[] processors) {
        this(parsedType, headers, processors, false, null);
    }

    /**
     * Get headers.
     *
     * @return headers
     */
    public String[] getHeaders() {
        return headers;
    }

    /**
     * Set headers.
     *
     * @param headers headers
     */
    public void setHeaders(final String[] headers) {
        this.headers = headers.clone();
    }

    /**
     * Get processors.
     *
     * @return processors
     */
    public CellProcessor[] getProcessors() {
        return processors.clone();
    }

    /**
     * Set processors.
     *
     * @param processors processors
     */
    public void setProcessors(final CellProcessor[] processors) {
        this.processors = processors;
    }

    /**
     * Get parsedType.
     *
     * @return parsedType
     */
    public Class getParsedType() {
        return parsedType;
    }

    /**
     * Set parsed Type.
     *
     * @param parsedType parsed Type
     */
    public void setParsedType(final Class parsedType) {
        this.parsedType = parsedType;
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public CsvPreference getPreference() {
        return preference;
    }
}
