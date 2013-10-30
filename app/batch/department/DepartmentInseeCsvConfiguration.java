package batch.department;

import batch.CsvParsingConfiguration;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

/**
 * Configuration CSV pour les données relatives aux Regions.
 *
 * @author Karim BENHDECH
 */
public final class DepartmentInseeCsvConfiguration {

    /**
     * Renvoie la configuration du parser CSV pour les départements.
     *
     * @return conf
     */
    public static CsvParsingConfiguration getConfiguration() {
        return new CsvParsingConfiguration(
                DepartmentInsee.class,
                new String[]{
                        "regionCode", // REGION - Code région
                        "departmentCode", // DEP - Code département
                        null, // CHEFLIEU - Chef-lieu de canton, d'arrondissement, de département, de département
                        null, // TNCC - Type de name en clair
                        "ncc", // NCC - Nom en clair (majuscules)
                        "nccenr" // NCCENR - Nom en clair (typographie riche)
                },
                new CellProcessor[]{
                        new NotNull(), // REGION - Code région
                        new NotNull(), // DEP - Code département
                        null, // CHEFLIEU - Chef-lieu de canton, d'arrondissement, de département, de département
                        null, // TNCC - Type de name en clair
                        new NotNull(), // NCC - Nom en clair (majuscules)
                        new NotNull() // NCCENR - Nom en clair (typographie riche)
                }
        );
    }
}