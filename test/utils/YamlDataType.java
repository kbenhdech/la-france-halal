package utils;

/**
 * Liste l'ensemble des fichiers yml utilisés par les tests.
 *
 * @author Karim BENHDECH
 */
public enum YamlDataType {
    /**
     * Tests des batchs.
     */
    BATCH("yml/batch/data-test-batch.yml"),

    /**
     * Tests d'intégration des APIs relatives aux adresses.
     */
    API_ADDRESSES("yml/integration/controllers/api-addresses.yml"),

    /**
     * Tests fonctionnels relative aux restaurants.
     */
    FUNCTIONAL_RESTAURANT("yml/functional/data-test-functional.yml");
    /**
     * Chemin vers le fichier de données.
     */
    public final String path;

    /**
     * Constructeur.
     *
     * @param path Chemin vers le fichier de données
     */
    private YamlDataType(final String path) {
        this.path = path;
    }
}
