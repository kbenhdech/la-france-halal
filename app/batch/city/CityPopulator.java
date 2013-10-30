package batch.city;

import batch.IPopulator;
import batch.ImportGeographiesDataBatch;
import batch.parsing.TransactionalCsvParser;
import models.geography.City;
import models.geography.Department;
import models.geography.Region;

import static batch.ImportGeographiesDataBatch.CITIES_COUNTER;

/**
 * Populator de l'OME pays.
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
        // Conversion du code de la région en Long
        String regionCode = cityInsee.regionCode;
        String departmentCode = cityInsee.departmentCode;
        String inseeCode = cityInsee.inseeCode;
        String cityName = cityInsee.nccenr;

        // On recherche la région en base
        Region region = Region.findByCode(regionCode);

        // On recherche le département en base
        Department department = Department.findByCode(departmentCode);

        // On recherche la ville en base
        City cityInDb = City.findByCode(regionCode, departmentCode, inseeCode);

        // Récupération du code postal
        String zipCode = ImportGeographiesDataBatch.citiesCode.get( departmentCode + inseeCode );

        // La ville
        City city = new City(inseeCode, zipCode, cityName, region, department);

        // Sauvegarde ou MAJ selon le cas
        if (cityInDb == null) { // Sauvegarde
            TransactionalCsvParser.SERVER.save(city);
        } else { // MAJ
            city.id = cityInDb.id;
            TransactionalCsvParser.SERVER.update(city);
        }

        CITIES_COUNTER++;
    }

}
