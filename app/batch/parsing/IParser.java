package batch.parsing;

import batch.exception.ParsingException;

import java.io.InputStream;
import java.util.List;

/**
 * Interface de parser de fichier.
 *
 * @param <T> type générique
 * @author ???
 */
public interface IParser<T> {

    /**
     * Parse à partir d'un flux InputStream.
     *
     * @param in l'entrée
     * @return la liste
     * @throws ParsingException exception générée par le parser
     */
    List<T> parse(InputStream in) throws ParsingException;

    /**
     * Parser le fichier passé en paramètre.
     *
     * @param filename chemin du fichier a parser
     * @return the list
     * @throws ParsingException exception générée par le parser
     */
    List<T> parse(String filename) throws ParsingException;
}