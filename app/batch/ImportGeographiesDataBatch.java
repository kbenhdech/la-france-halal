package batch;

import batch.city.CityInseeCsvConfiguration;
import batch.city.CityPopulator;
import batch.code.CityCodeCsvConfiguration;
import batch.code.CityCodePopulator;
import batch.department.DepartmentInseeCsvConfiguration;
import batch.department.DepartmentPopulator;
import batch.parsing.CsvParser;
import batch.parsing.TransactionalCsvParser;
import batch.region.RegionInseeCsvConfiguration;
import batch.region.RegionPopulator;
import batch.strategy.PopulateStrategy;
import com.google.common.base.Stopwatch;
import models.geography.City;
import models.geography.Department;
import models.geography.Region;
import play.Logger;
import play.Play;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Classe d'import des données géographiques.
 *
 * @author Karim BENHDECH
 */
public final class ImportGeographiesDataBatch {

    /**
     * Le chemin vers le répertoire des fichiers sources.
     */
    public static String SOURCE_DIRECTORY = Play.application().configuration().getString("batch.directory.source");
    /**
     * Le nom du fichier qui contient les régions de France.
     */
    public static String REGIONS_FILE = Play.application().configuration().getString("batch.file.regions");
    /**
     * Le nom du fichier qui contient les départments de France.
     */
    public static String DEPARTMENTS_FILE = Play.application().configuration().getString("batch.file.departments");
    /**
     * Le nom du fichier qui contient la table de correspondance codes insee/postaux.
     */
    public static String CODES_FILE = Play.application().configuration().getString("batch.file.codes");
    /**
     * Le nom du fichier qui contient les communes de France.
     */
    public static String CITIES_FILE = Play.application().configuration().getString("batch.file.cities");
    /**
     * Cache : zipCode => inseeCode.
     */
    public static Map<String, String> citiesCode = new HashMap<String, String>();
    /**
     * Compteur du nombre de régions.
     */
    public static int REGIONS_COUNTER = 0;
    /**
     * Compteur du nombre de départements.
     */
    public static int DEPARTMENTS_COUNTER = 0;
    /**
     * Compteur du nombre de villes.
     */
    public static int CITIES_COUNTER = 0;
    /**
     * Compteur du nombre de codes postaux trouvés.
     */
    public static int ZIPCODE_FIND_COUNTER = 0;

    /**
     * Traitement des régions.
     */
    public static void batchRegions() {
        new PopulateStrategy(
                SOURCE_DIRECTORY + REGIONS_FILE,
                new CsvParser(RegionInseeCsvConfiguration.getConfiguration()),
                new RegionPopulator()
        ).execute();

        Logger.info("Nombre de régions récupérées : " + REGIONS_COUNTER);
    }

    /**
     * Traitement des départements.
     */
    public static void batchDepartments() {
        new PopulateStrategy(
                SOURCE_DIRECTORY + DEPARTMENTS_FILE,
                new CsvParser(DepartmentInseeCsvConfiguration.getConfiguration()),
                new DepartmentPopulator()
        ).execute();

        Logger.info("Nombre de départements récupérées : " + DEPARTMENTS_COUNTER);
    }

    /**
     * Traitement des correspondances entre codes insee et postaux.
     */
    public static void batchCitiesCode() {
        new PopulateStrategy(
                SOURCE_DIRECTORY + CODES_FILE,
                new CsvParser(CityCodeCsvConfiguration.getConfiguration()),
                new CityCodePopulator()
        ).execute();

        Logger.info("Nombre de correspondances (code insee, code postal) récupérées : " + ZIPCODE_FIND_COUNTER);
    }

    /**
     * Traitement des villes.
     */
    public static void batchCities() {
        new PopulateStrategy(
                SOURCE_DIRECTORY + CITIES_FILE,
                new TransactionalCsvParser(CityInseeCsvConfiguration.getConfiguration()),
                new CityPopulator()
        ).execute();

        Logger.info("Nombre de communes récupérées : " + CITIES_COUNTER);
    }

    /**
     * Nettoyage des données en mémoire.
     */
    public static void clean() {
        REGIONS_COUNTER = 0;
        DEPARTMENTS_COUNTER = 0;
        CITIES_COUNTER = 0;
        ZIPCODE_FIND_COUNTER = 0;
        citiesCode.clear();
    }

    /**
     * Exécution du batch.
     */
    public static void execute() {
        // Timer - Début
        Stopwatch timer = new Stopwatch();
        timer.start();

        Logger.info("DEBUT du batch de récupération des données géographiques");

        Logger.info("AVANT EXECUTION EN BASE - Nombre de régions : " + Region.count());
        Logger.info("AVANT EXECUTION EN BASE - Nombre de départements : " + Department.count());
        Logger.info("AVANT EXECUTION EN BASE - Nombre de villes : " + City.count());
        Logger.info("AVANT EXECUTION EN BASE - Nombre de villes avec un code postal : " + City.countWithZipCode());

        Logger.info("Traitement de l'import des régions - DEBUT");
        batchRegions();
        Logger.info("Traitement de l'import des régions - FIN");

        Logger.info("Traitement de l'import des départements - DEBUT");
        batchDepartments();
        Logger.info("Traitement de l'import des départements - FIN");

        Logger.info("Traitement de l'import des correspondances codes insee/postaux - DEBUT");
        batchCitiesCode();
        Logger.info("Traitement de l'import des correspondances codes insee/postaux - FIN");

        Logger.info("Traitement de l'import des communes - DEBUT");
        batchCities();
        Logger.info("Traitement de l'import des communes - FIN");

        Logger.info("APRES EXECUTION EN BASE - Nombre de régions : " + Region.count());
        Logger.info("APRES EXECUTION EN BASE - Nombre de départements : " + Department.count());
        Logger.info("APRES EXECUTION EN BASE - Nombre de villes : " + City.count());
        Logger.info("APRES EXECUTION EN BASE - Nombre de villes avec un code postal : " + City.countWithZipCode());

        Logger.info("FIN du batch de récupération des données géographiques");

        // Timer - Fin
        Logger.info("Le traitement a duré " + timer.elapsedTime(TimeUnit.SECONDS) + 's');
        timer.stop();
    }

}
