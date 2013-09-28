package batch.strategy;

import batch.IPopulator;
import batch.parsing.IParserWithStrategy;

/**
 * Traitements communs à toutes les stratégies d'intégration de fichiers.
 */
public abstract class AbstractIntegrationStrategy<T> implements IIntegrationStrategy<T> {
    /**
     * Nom du fichier à traiter.
     */
    protected String fileName;

    /**
     * IParser utilisé pour le traitement du fichier.
     */
    protected IParserWithStrategy parser;

    /**
     * IPopulator utilisé pour le traitement du fichier.
     */
    protected IPopulator populator;

    /**
     * Le logger.
     */
    //private static final Logger LOGGER = Logger.getLogger(AbstractIntegrationStrategy.class);

    /**
     * Constructeur.
     *
     * @param fileName  fichier à parser
     * @param parser    parser
     * @param populator populator
     */
    protected AbstractIntegrationStrategy(final String fileName,
                                          final IParserWithStrategy parser,
                                          final IPopulator populator) {
        this.fileName = fileName;
        this.parser = parser;
        this.parser.setStrategy(this);
        this.populator = populator;
    }

    @Override
    public void execute() {
        // parse l'intégralité du fichier
        this.parser.parse(fileName);
    }

    @Override
    public final void lineCallBack(final T t) {
            specificLineCallBack(t);
    }

    /**
     * Traitement spécifique effectué à chaque ligne parsée.
     *
     * @param t un objet du fichier
     */
    protected abstract void specificLineCallBack(final T t);
}
