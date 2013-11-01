package utils;

import play.test.FakeApplication;
import play.test.Helpers;

import java.util.Map;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.start;

/**
 * Classe utilitaire pour tous les tests nécessitant une FakeApplication Play.
 *
 * @author Karim BENHDECH
 */
public final class FakeApplicationTestSuite {

    /**
     * Fake Application.
     */
    private static FakeApplication app;

    /**
     * Démarrage de la Fake Application.
     *
     * @param conf la configuration
     */
    public static void startApp(final Map<String, String> conf) {
        app = fakeApplication(conf);
        start(app);
    }

    /**
     * Arrêt de la FakeApplication.
     */
    public static void stopApp() {
        Helpers.stop(app);
    }
}
