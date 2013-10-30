package batch.region;

import batch.IPopulator;
import models.geography.Region;

import static batch.ImportGeographiesDataBatch.REGIONS_COUNTER;

/**
 * Populator de la région INSEE.
 *
 * @author Karim BENHDECH
 */
public class RegionPopulator implements IPopulator<RegionInsee> {

    /**
     * Populate.
     *
     * @param regionInsee une région
     */
    @Override
    public void populate(final RegionInsee regionInsee) {
        String regionCode = regionInsee.regionCode;
        String regionName = regionInsee.nccenr;

        // On recherche la région en base
        Region regionInDb = Region.findByCode(regionCode);

        // La région
        Region regionNew = new Region(regionCode, regionName);

        // Sauvegarde ou MAJ selon le cas
        if (regionInDb == null) { // Sauvegarde
            regionNew.save();
        } else { // MAJ du nom (en cas de changement)
            regionInDb.name = regionName;
            regionInDb.update(regionInDb.id);
        }

        REGIONS_COUNTER++;
    }

}
