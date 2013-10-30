package batch.code;

import batch.parsing.CsvParsingConfiguration;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;

/**
 * Configuration CSV pour la table de correspondance code insee/code postal.
 *
 * @author Karim BENHDECH
 */
public final class CityCodeCsvConfiguration {

    /**
     * Constructeur par défaut, privé.
     */
    private CityCodeCsvConfiguration() {
    }

    /**
     * Renvoie la configuration du parser CSV pour les communes.
     *
     * @return conf
     */
    public static CsvParsingConfiguration getConfiguration() {
        return new CsvParsingConfiguration(
                CityCode.class,
                new String[]{
                        null, // Commune
                        "zipCode", // Code postale
                        null, // Département
                        "inseeCode" // Code insee
                },
                new CellProcessor[]{
                        null, // Commune
                        new Optional(), // Code postale
                        null, // Département
                        new Optional() // Code insee
                }
        );
    }
}
