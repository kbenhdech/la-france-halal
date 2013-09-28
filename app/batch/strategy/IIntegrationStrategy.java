package batch.strategy;

/**
 * Stratégie d'intégration des OME.
 */
public interface IIntegrationStrategy<T> {
    /**
     * Lance le traitement de l'OME.
     */
    void execute();

    /**
     * Effectue le traitement sur un élément de l'OME.
     * @param ome élément de l'OME
     */
    void lineCallBack(T ome);
}
