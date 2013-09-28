package batch.parsing;

import batch.strategy.IIntegrationStrategy;

/**
 * Interface implémentée par les classes qui nécessitent un accès à IIntegrationStrategy.
 */
public interface IParserWithStrategy<T> extends IParser<T> {

    /**
     * Get IIntegrationStrategy.
     *
     * @return the IIntegrationStrategy
     */
    IIntegrationStrategy getStrategy();

    /**
     * Set IIntegrationStrategy.
     *
     * @param strategy IIntegrationStrategy.
     */
    void setStrategy(IIntegrationStrategy strategy);
}
