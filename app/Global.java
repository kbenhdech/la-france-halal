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
                Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load(Play.application().configuration().getString("yml.file.path"));
                Ebean.save(all.get("restaurants"));
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


