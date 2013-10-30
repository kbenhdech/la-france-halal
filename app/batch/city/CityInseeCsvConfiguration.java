package batch.city;

import batch.parsing.CsvParsingConfiguration;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

/**
 * Configuration CSV pour les communes Insee.
 *
 * @author Karim BENHDECH
 */
public final class CityInseeCsvConfiguration {

    /**
     * Constructeur par défaut, privé.
     */
    private CityInseeCsvConfiguration() {
    }

    /**
     * Renvoie la configuration du parser CSV pour les communes.
     *
     * @return conf
     */
    public static CsvParsingConfiguration getConfiguration() {
        return new CsvParsingConfiguration(
                CityInsee.class,
                new String[]{
                        null, // CDC - Code actualité de la commune
                        null, // CHEFLIEU - Chef-lieu de canton, d'arrondissement, de département, de région
                        "regionCode", // REG - Code région
                        "departmentCode", // DEP - Code département
                        "inseeCode", // COM - Code de la commune
                        null, // AR -
                        null,  // CT - Code canton
                        null,  // Type de name en clair
                        null,  // TNCC - Article (majuscules)
                        "ncc",  // NCC - Nom en clair (majuscules)
                        null,  // ARTMIN - Article (typographie riche)
                        "nccenr"  // NCCENR - Nom en clair (typographie riche)
                },
                new CellProcessor[]{
                        null, // CDC - Code actualité de la commune
                        null, // CHEFLIEU - Chef-lieu de canton, d'arrondissement, de département, de région
                        new NotNull(), // REG - Code de la région
                        new NotNull(), // DEP - Code département
                        new NotNull(), // COM - Code la commune
                        null, // AR -
                        null,  // CT - Code canton
                        null,  // TNCC - Type de name en clair
                        null,  // ARTMAJ - Article (majuscules)
                        new NotNull(),  // NCC - Nom en clair (majuscules)
                        null,  // ARTMIN - Article (typographie riche)
                        new NotNull()  // NCCENR - Nom en clair (typographie riche)
                }
        );
    }
}
