package batch.parsing;

import batch.exception.ParsingException;
import batch.strategy.IIntegrationStrategy;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Parser CSV utilisé pour traiter les fichiers.
 */
public class CsvParser<T> implements IParserWithStrategy<T> {
    /**
     * Préférences pour le traitement des fichiers SerfRH :
     * - Délimiteur de texte,
     * - Séparateur de champ,
     * - Saut de ligne.
     */
    protected static final CsvPreference INSEE_PREFERENCE = new CsvPreference.Builder('"', '\t', "\n").build();

    /**
     * Encodage des fichiers issus de SerfRh.
     */
    private static final String SERF_RH_CHARSET = "UTF-8"; //"ISO-8859-15";

    /**
     * Stratégie utilisée pour traiter chaque ligne.
     */
    protected IIntegrationStrategy strategy;

    /**
     * Configuration utlisée pour traiter le fichier.
     */
    protected CsvParsingConfiguration configuration;

    /**
     * Constructeur.
     *
     * @param configuration configuration du fichier à traitée.
     */
    public CsvParser(final CsvParsingConfiguration configuration) {
        this.setConfiguration(configuration);
    }

    /**
     * Parse la source fournie et transmet l'objet généré à la stratégie.
     *
     * @param input source à parser
     * @throws ParsingException    si la configuration est mal renseignée
     * @throws java.io.IOException erreur I/O
     */
    protected void parse(final Reader input) throws ParsingException, IOException {
        if ((configuration.getHeaders() == null)
                || (configuration.getProcessors() == null)
                || (configuration.getParsedType() == null)
                || (strategy == null)) {
            throw new ParsingException("Tous les éléments de la configuration du parser doivent être renseignés.");
        }

        CsvPreference prefs = configuration.getPreference();
        if (prefs == null) {
            prefs =  INSEE_PREFERENCE;
        }
        CsvBeanReader csvReader = new CsvBeanReader(input, prefs);

        // Ne pas prendre en compte la première ligne d'entête.
        if(configuration.hasHeader()){
            csvReader.getHeader(true);
        }
        T parsedClass;
        try {
            while ((parsedClass = (T) csvReader.read(
                    configuration.getParsedType(),
                    configuration.getHeaders(),
                    configuration.getProcessors())) != null) {
                getStrategy().lineCallBack(parsedClass);
            }
        } catch (SuperCsvException e) {
            e.printStackTrace();
            throw new ParsingException("Erreur lors du parsing de : '" +
                    configuration.getParsedType().getSimpleName() + "' : " +
                    e.getMessage() + "; contexte : " + e.getCsvContext());
        } finally {
            csvReader.close();
        }
    }

    /**
     * Parse.
     *
     * @param filename chemin du fichier a parser
     * @return une liste
     * @throws ParsingException une exception
     */
    @Override
    public List<T> parse(final String filename) throws ParsingException {
        try {
            InputStreamReader input = new InputStreamReader(new FileInputStream(filename),
                    Charset.forName(SERF_RH_CHARSET));
            try {
                this.parse(input);
            } finally {
                input.close();
            }
        } catch (FileNotFoundException e) {
            throw new ParsingException("Impossible d'ouvrir le fichier : " + filename);
        } catch (IOException e) {
            throw new ParsingException("Erreur lors du parsing du fichier '" + filename + "' " + e.getMessage());
        }
        return null;
    }

    /**
     * Parse.
     *
     * @param in l'entrée
     * @return une liste
     */
    @Override
    public List<T> parse(final InputStream in) {
        try {
            InputStreamReader input = new InputStreamReader(in);
            try {
                this.parse(input);
            } finally {
                input.close();
            }
        } catch (IOException e) {
            throw new ParsingException("Erreur lors du parsing du flux : " + e.getMessage());
        }

        return null;
    }

    /**
     * Définit la configuration à utiliser.
     *
     * @param configuration configuration utlisée pour traiter le fichier.
     */
    public void setConfiguration(final CsvParsingConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Setter.
     *
     * @param strategy IIntegrationStrategy.
     */
    @Override
    public void setStrategy(final IIntegrationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Getter.
     *
     * @return IIntegrationStrategy.
     */
    @Override
    public IIntegrationStrategy getStrategy() {
        return strategy;
    }
}
