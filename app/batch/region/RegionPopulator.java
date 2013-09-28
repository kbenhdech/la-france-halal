package batch.region;

import batch.IPopulator;
import models.geography.Region;

import static batch.ImportGeographiesDataBatch.REGIONS_COUNTER;

/**
 * Populator de l'OME pays.
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
        // Conversion du code de la région en Long
        String regionCode = regionInsee.regionCode;
        String regionName = regionInsee.nccenr;

        // On recherche la région en base
        Region regionInDb = Region.findByCode(regionCode);

        // La région
        Region region = new Region(regionCode, regionName);

        // Sauvegarde ou MAJ selon le cas
        if (regionInDb == null) { // Sauvegarde
            region.save();
        } else { // MAJ
            regionInDb.name = regionName;
            regionInDb.update(regionInDb.id);
        }

        REGIONS_COUNTER++;
    }

}
