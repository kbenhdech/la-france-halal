package batch.department;

import batch.IPopulator;
import models.geography.Department;
import models.geography.Region;

import static batch.ImportGeographiesDataBatch.DEPARTMENTS_COUNTER;

/**
 * Populator du département INSEE.
 *
 * @author Karim BENHDECH
 */
public class DepartmentPopulator implements IPopulator<DepartmentInsee> {

    /**
     * Populate.
     *
     * @param departmentInsee un département
     */
    @Override
    public void populate(final DepartmentInsee departmentInsee) {
        String regionCode = departmentInsee.regionCode;
        String departmentCode = departmentInsee.departmentCode;
        String departmentName = departmentInsee.nccenr;

        // On recherche la région en base
        Region regionInDb = Region.findByCode(regionCode);

        // Si la région du département n'existe pas
        // On ne fait rien
        if (regionInDb != null) {

            // On recherche la département en base
            Department departmentInDb = Department.findByCode(departmentCode);

            // La département
            Department departmentNew = new Department(departmentCode, departmentName, regionInDb);

            // Sauvegarde ou MAJ selon le cas
            if (departmentInDb == null) { // Sauvegarde
                departmentNew.save();
            } else { // MAJ
                departmentInDb.name = departmentName; // MAJ du nom (en cas de changement)
                departmentInDb.region = regionInDb;   // MAJ de la région (en cas de changement)
                departmentInDb.update(departmentInDb.id);
            }

            DEPARTMENTS_COUNTER++;
        }
    }

}
