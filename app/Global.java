import com.avaje.ebean.Ebean;
import models.Restaurant;
import play.Application;
import play.GlobalSettings;
import play.Play;
import play.libs.Yaml;
import utils.DataBinders;

import java.util.List;
import java.util.Map;

/**
 * Parametres globaux.
 * Chargé au lanecement.
 *
 * @author Karim BENHDECH
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(final Application app) {
        StartTasks.insertData(app);

        StartTasks.registerDataBinder();
    }

    /**
     * Tâches executées au démarrage de l'application.
     */
    static class StartTasks {

        /**
         * Insertion de données via le fichier yaml.
         *
         * @param app application
         */
        public static void insertData(final Application app) {
            // Indique si les données yml doivent être chargées (false en production)
            final boolean insert = Play.application().configuration().getBoolean("yml.insert");

            if (insert && Ebean.find(Restaurant.class).findRowCount() == 0) {
                Map<String, List<Object>> ymlData = (Map<String, List<Object>>) Yaml.load(Play.application().configuration().getString("yml.file.path"));
                saveModel(ymlData, "restaurants");

                saveModel(ymlData, "regions");
                saveModel(ymlData, "departments");
                saveModel(ymlData, "cities");
            }
        }

        /**
         * Sauvegarde des données d'un model.
         * Uniquement si les données sont présentes.
         *
         * @param ymlData les données du fichier yaml
         * @param model   le nom des données d'un model dans le fichier yaml
         */
        private static void saveModel(final Map<String, List<Object>> ymlData, final String model) {
            if (ymlData != null && ymlData.get(model) != null) {
                Ebean.save(ymlData.get(model));
            }
        }

        /**
         * Enregistrement des databinders.
         */
        public static void registerDataBinder() {
            try {
                DataBinders.addDataBinders();
            } catch (NoSuchFieldException e) {
                System.exit(0);
            }
        }

    }

}


