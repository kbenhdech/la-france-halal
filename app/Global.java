import com.avaje.ebean.Ebean;
import models.Restaurant;
import play.Application;
import play.GlobalSettings;
import play.Play;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

/**
 * Parametres globaux.
 *
 * @author Karim BENHDECH
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(final Application app) {
        StartTasks.insert(app);
    }

    /**
     * Tâches executées au démarrage de l'appli.
     */
    static class StartTasks {

        /**
         * Insertion de données via YAML.
         *
         * @param app application
         */
        public static void insert(final Application app) {
            boolean insert = Play.application().configuration().getBoolean("yml.insert");
            if (insert && Ebean.find(Restaurant.class).findRowCount() == 0) {

                Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load(Play.application().configuration().getString("yml.file.path"));
                Ebean.save(all.get("restaurants"));
            }
        }
    }
}


