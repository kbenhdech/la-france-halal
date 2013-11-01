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
     * Avec possibilité de définir un jeu de données.
     *
     * @return Config de test
     */
    public static Map<String, String> defautlConfiguration(final YamlDataType yamlDataType) {
        Map<String, String> conf = new HashMap<String, String>();
        Config config = ConfigFactory.parseFileAnySyntax(new File(
                "test/resources/application-test-default.conf"));
        config = config.resolve();
        for (Map.Entry<String, ConfigValue> entry : config.entrySet()) {
            ConfigValue value = entry.getValue();
            conf.put(entry.getKey(), value.unwrapped().toString());
        }
        conf.putAll(inMemoryDatabase());

        if (yamlDataType != null) {
            conf.put("yml.file.path", yamlDataType.path);
        }

        return conf;
    }

    /**
     * Configuration par défaut.
     *
     * @return Config de test
     */
    public static Map<String, String> defautlConfiguration() {
        return defautlConfiguration(null);
    }

}
