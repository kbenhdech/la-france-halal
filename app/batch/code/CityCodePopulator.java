package batch.code;

import batch.IPopulator;
import batch.ImportGeographiesDataBatch;

import static batch.ImportGeographiesDataBatch.ZIPCODE_FIND_COUNTER;

/**
 * Populator des correspondances codes insee et codes postaux.
 * Mise en cache.
 * Pas de modification en base à cette étape.
 *
 * @author Karim BENHDECH
 */
public class CityCodePopulator implements IPopulator<CityCode> {

    /**
     * Populate la table de correspondance.
     *
     * @param cityCode une correpondance
     */
    @Override
    public void populate(final CityCode cityCode) {

        // On traite la donnée uniquement si elle possède un code postal et un code insee
        if (cityCode.zipCode != null && cityCode.inseeCode != null) {

            final String inseeCode = on5igits(cityCode.inseeCode);
            final String zipCode = on5igits(cityCode.zipCode);

            // On met en cache la correspondance en code insee et code postal
            ImportGeographiesDataBatch.citiesCode.put(inseeCode, zipCode);

            ZIPCODE_FIND_COUNTER++;
        }
    }

    /**
     * Transformation d'une chaîne de caractères.
     * Afin qu'elle fasse 5 de longueur.
     * Complété par des 0.
     *
     * @param code uen chaîne de caractères
     * @return
     */
    private String on5igits(final String code) {
        switch (code.length()) {
            case 5:
                break;
            case 4:
                return "0" + code;
            case 3:
                return "00" + code;
            case 2:
                return "000" + code;
            case 1:
                return "0000" + code;
            default:
                break; // impossible
        }
        return code;
    }

}
