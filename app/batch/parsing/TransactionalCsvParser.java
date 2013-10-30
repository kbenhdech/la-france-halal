package batch.parsing;

import batch.exception.ParsingException;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Reader;

/**
 * Parser avec gestion de transaction, pour de gros volumes de données.
 *
 * @author Karim BENHDECH
 */
public class TransactionalCsvParser<T> extends CsvParser {

    /**
     * Instance de la classe EbeanServer.
     */
    public static EbeanServer SERVER = Ebean.getServer(null);

    /**
     * Constructeur.
     *
     * @param configuration configuration du fichier à traitée.
     */
    public TransactionalCsvParser(final CsvParsingConfiguration configuration) {
        super(configuration);
    }

    /**
     * Parse la source fournie et transmet l'objet généré à la stratégie.
     *
     * @param input source à parser
     * @throws batch.exception.ParsingException
     *                             si la configuration est mal renseignée
     * @throws java.io.IOException erreur I/O
     */
    @Override
    protected void parse(final Reader input) throws ParsingException, IOException {
        if ((configuration.getHeaders() == null)
                || (configuration.getProcessors() == null)
                || (configuration.getParsedType() == null)
                || (strategy == null)) {
            throw new ParsingException("Tous les éléments de la configuration du parser doivent être renseignés.");
        }

        CsvPreference prefs = configuration.getPreference();
        if (prefs == null) {
            prefs = INSEE_PREFERENCE;
        }
        CsvBeanReader csvReader = new CsvBeanReader(input, prefs);

        // Ne pas prendre en compte la première ligne d'entête.
        if (configuration.hasHeader()) {
            csvReader.getHeader(true);
        }
        T parsedClass;
        try {
            SERVER = Ebean.getServer(null);
            Transaction transaction = SERVER.beginTransaction();
            try {
                // Use JDBC batch API with a batch size of 100
                transaction.setBatchSize(100);
                // Don't bother getting generated keys
                transaction.setBatchGetGeneratedKeys(false);
                // Skip cascading persist
                transaction.setPersistCascade(false);

                transaction.setBatchMode(false);
                while ((parsedClass = (T) csvReader.read(
                        configuration.getParsedType(),
                        configuration.getHeaders(),
                        configuration.getProcessors())) != null) {
                    getStrategy().lineCallBack(parsedClass);

                }
                transaction.commit();
            } finally {
                transaction.end();
                    csvReader.close();
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

}
