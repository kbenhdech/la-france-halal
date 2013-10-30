package batch.code;

import batch.IPopulator;
import batch.ImportGeographiesDataBatch;

import static batch.ImportGeographiesDataBatch.ZIPCODE_FIND_COUNTER;

/**
 * Populator de la table de correspondance codes insee/postaux.
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

        if (cityCode.zipCode != null && cityCode.inseeCode != null) {
            // Code insee sur 5 digits
            String inseeCode = cityCode.inseeCode;
            if (inseeCode.length() == 4) {
                inseeCode = "0" + inseeCode;
            }

            // On met en cache la région
            ImportGeographiesDataBatch.citiesCode.put(inseeCode, cityCode.zipCode);

            ZIPCODE_FIND_COUNTER++;
        }
    }

}
