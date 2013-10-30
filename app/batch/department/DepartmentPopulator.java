package batch.department;

import batch.IPopulator;
import models.geography.Department;
import models.geography.Region;

import static batch.ImportGeographiesDataBatch.DEPARTMENTS_COUNTER;

/**
 * Populator de l'OME pays.
 *
 * @author Karim BENHDECH
 */
public class DepartmentPopulator implements IPopulator<DepartmentInsee> {

    /**
     *  Pupulate.
     *
     * @param departmentInsee un département
     */
    @Override
    public void populate(final DepartmentInsee departmentInsee) {
        // Conversion du code de la département en Long
        String regionCode = departmentInsee.regionCode;
        String departmentCode = departmentInsee.departmentCode;
        String departmentName = departmentInsee.nccenr;

        // On recherche la région en base
        Region region = Region.findByCode(regionCode);

        // On recherche la département en base
        Department departmentInDb = Department.findByCode(departmentCode);

        // La département
        Department department = new Department(departmentCode, departmentName, region);

        // Sauvegarde ou MAJ selon le cas
        if (departmentInDb == null) { // Sauvegarde
            department.save();
        } else { // MAJ
            departmentInDb.name = departmentName;
            departmentInDb.update(departmentInDb.id);
        }

        DEPARTMENTS_COUNTER++;
    }

}
