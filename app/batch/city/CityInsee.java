package batch.city;

/**
 * Classe utilisée pour l'intégration des régions.
 * Source : INSEE
 *
 * @author Karim BENHDECH
 */
public class CityInsee {

    /**
     * Code région.
     */
    public String regionCode;
    /**
     * Code département.
     */
    public String departmentCode;
    /**
     * Code insee de la ville.
     * Pas tout à fait.
     * Uniquement le code ville, non préfixé par le code département.
     */
    public String inseeCode;
    /**
     * Nom en clair (majuscules)
     */
    public String ncc;
    /**
     * Nom en clair (typographie riche).
     */
    public String nccenr;

}
