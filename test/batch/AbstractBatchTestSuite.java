package batch;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import play.test.FakeApplication;
import play.test.Helpers;
import utils.FakeApplicationConf;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.start;

/**
 * Classe à étendre pour tous les tests nécessitant une FakeApplication Play.
 * Configurée pour les tests relatives aux batchs.
 *
 * @author Karim BENHDECH
 */
abstract public class AbstractBatchTestSuite {

    /**
     * Fake Application.
     */
    private static FakeApplication app;

    /**
     * Démarrage de la Fake Application.
     */
    @BeforeClass
    public static void startApp() {
        app = fakeApplication(FakeApplicationConf.batchConfiguration());
        start(app);
    }

    /**
     * Arrêt de la FakeApplication.
     */
    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }
}
