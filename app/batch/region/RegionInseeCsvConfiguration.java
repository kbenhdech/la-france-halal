package batch.region;

import batch.CsvParsingConfiguration;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

/**
 * Configuration CSV pour les données relatives aux Regions.
 *
 * @author Karim BENHDECH
 */
public final class RegionInseeCsvConfiguration {

    /**
     * Renvoie la configuration du parser CSV pour les Régions.
     *
     * @return conf
     */
    public static CsvParsingConfiguration getConfiguration() {
        return new CsvParsingConfiguration(
                RegionInsee.class,
                new String[]{
                        "regionCode", // REGION - Code région
                        null, // CHEFLIEU - Chef-lieu de canton, d'arrondissement, de département, de région
                        null, // TNCC - Type de name en clair
                        "ncc", // NCC - Nom en clair (majuscules)
                        "nccenr" // NCCENR - Nom en clair (typographie riche)
                },
                new CellProcessor[]{
                        new NotNull(), // REGION - Code région
                        null, // CHEFLIEU - Chef-lieu de canton, d'arrondissement, de département, de région
                        null, // TNCC - Type de name en clair
                        new NotNull(), // NCC - Nom en clair (majuscules)
                        new NotNull() // NCCENR - Nom en clair (typographie riche)
                }
        );
    }
}
