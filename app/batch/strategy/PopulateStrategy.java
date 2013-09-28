package batch.strategy;

import batch.IPopulator;
import batch.parsing.IParserWithStrategy;

/**
 * Stratégie d'intégration d'OME comprenant les étapes :
 * <li>- Parsing,</li>
 * <li>- Populate.</li>
 */
public class PopulateStrategy<T> extends AbstractIntegrationStrategy<T> {
    /**
     * Constructeur.
     * @param fileName fichier à parser
     * @param parser parser
     * @param populator populator
     */
    public PopulateStrategy(final String fileName,
                            final IParserWithStrategy parser,
                            final IPopulator populator) {
        super(fileName, parser, populator);
    }

    /**
     * Call back.
     *
     * @param t un objet du fichier
     */
    @Override
    protected void specificLineCallBack(final T t) {
        populator.populate(t);
    }

}
