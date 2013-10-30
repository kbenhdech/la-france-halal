package utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static play.test.Helpers.inMemoryDatabase;

/**
 * Retourne une defautlConfiguration de tests.
 * Pour la fake application.
 *
 * @author Karim BENHDECH
 */
public class FakeApplicationConf {

    /**
     * Configuration par défaut.
     * Chargement de la conf de test située dans "test/resources/test-application.conf".
     *
     * @return Config de test
     */
    public static Map<String, String> defautlConfiguration() {
        Map<String, String> conf = new HashMap<String, String>();
        Config config = ConfigFactory.parseFileAnySyntax(new File(
                "test/resources/application-test-default.conf"));
        config = config.resolve();
        for (Map.Entry<String, ConfigValue> entry : config.entrySet()) {
            ConfigValue value = entry.getValue();
            conf.put(entry.getKey(), value.unwrapped().toString());
        }
        conf.putAll(inMemoryDatabase());
        return conf;
    }

    /**
     * Configuration pour les batch.
     *
     * @return Config de test
     */
    public static Map<String, String> batchConfiguration() {
        Map<String, String> conf = defautlConfiguration();
        conf.put("yml.file.path", "test/batch/resources/data-test-batch.yml");
        return conf;
    }

    /**
     * Configuration pour les tests d'intégrations.
     *
     * @return Config de test
     */
    public static Map<String, String> integrationConfiguration() {
        Map<String, String> conf = defautlConfiguration();
        conf.put("yml.file.path", "test/functional/resources/data-test-functional.yml");
        return conf;
    }
}
