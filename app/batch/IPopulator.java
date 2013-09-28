package batch;

/**
 * Interface utilisée pour intégrer les données des fichiers en base de données.
 */
public interface IPopulator<T> {
    /**
     * Effectue les opérations nécessaires pour intégrer l'objet.
     *
     * @param t l'objet à insérer
     */
    void populate(T t);
}
