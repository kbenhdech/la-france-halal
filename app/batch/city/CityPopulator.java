package batch.city;

import batch.IPopulator;
import batch.ImportGeographiesDataBatch;
import batch.parsing.TransactionalCsvParser;
import models.geography.City;
import models.geography.Department;
import models.geography.Region;

import static batch.ImportGeographiesDataBatch.CITIES_COUNTER;

/**
 * Populator de des villes INSEE.
 *
 * @author Karim BENHDECH
 */
public class CityPopulator implements IPopulator<CityInsee> {

    /**
     * Populate.
     *
     * @param cityInsee Une commune
     */
    @Override
    public void populate(final CityInsee cityInsee) {
        String regionCode = cityInsee.regionCode;
        String departmentCode = cityInsee.departmentCode;
        String inseeCode = cityInsee.inseeCode;
        String cityName = cityInsee.nccenr;

        // On recherche la région en base
        Region regionInDb = Region.findByCode(regionCode);

        // On recherche le département en base
        Department departmentInDb = Department.findByCode(departmentCode);

        // Si le département ou la région de la ville ne sont pas trouvés
        // On ne fait rien
        if (regionInDb != null && departmentInDb != null) {

            // On recherche la ville en base
            City cityInDb = City.findByCode(regionCode, departmentCode, inseeCode);

            // Récupération du code postal
            String zipCode = ImportGeographiesDataBatch.citiesCode.get(departmentCode + inseeCode);

            // La ville
            City cityNew = new City(inseeCode, zipCode, cityName, regionInDb, departmentInDb);

            // Sauvegarde ou MAJ selon le cas
            if (cityInDb == null) { // Sauvegarde
                TransactionalCsvParser.SERVER.save(cityNew);
            } else { // MAJ (Il peut y avoir une perte de code postal entre deux exécutions)
                cityNew.id = cityInDb.id;
                TransactionalCsvParser.SERVER.update(cityNew);
            }

            CITIES_COUNTER++;
        }
    }

}
