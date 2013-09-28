package batch;

import play.api.DefaultApplication;
import play.api.Mode;

import java.io.File;

/**
 * Classe d'import des données géographiques.
 * Peux être lancée de manière indépendante.
 *
 * @author Karim BENHDECH
 */
public class ImportGeographiesDataBatchApp {

    /**
     * Main.
     *
     * @param args arguments de la commande
     */
    public static void main(final String[] args) {
        DefaultApplication application =
                new DefaultApplication(new File(args[0]), ImportGeographiesDataBatchApp.class.getClassLoader(), null, Mode.Prod());

        play.api.Play.start(application);
        ImportGeographiesDataBatch.execute();
    }

}
