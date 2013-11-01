package batch;

import models.geography.Department;
import org.junit.Test;
import play.test.FakeApplication;
import play.test.Helpers;
import utils.FakeApplicationConf;
import utils.YamlDataType;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Tests de récupération des département.
 *
 * @author Karim BENHDECH
 */
public class DepartmentBatchTest {

    @Test
    public void execute() {
        FakeApplication app = fakeApplication(FakeApplicationConf.defautlConfiguration(YamlDataType.BATCH));

        running(app, new Runnable() {
            public void run() {
                ImportGeographiesDataBatch.SOURCE_DIRECTORY = "test/batch/resources/";
                ImportGeographiesDataBatch.REGIONS_FILE = "regions.origin.csv";

                // Paramètrage avant éxécution
                ImportGeographiesDataBatch.clean();
                ImportGeographiesDataBatch.DEPARTMENTS_FILE = "departments.origin.csv";

                // Exécution du batch
                // On test que les 8 départements sont bien récupérées
                // La haute corse ne doit pas l'être car sa région n'est pas en base
                assertThat(Department.count()).isEqualTo(0);
                ImportGeographiesDataBatch.batchRegions();
                ImportGeographiesDataBatch.batchDepartments();
                assertThat(Department.count()).isEqualTo(7);

                // // La haute corse ne doit pas être présente car sa région n'est pas en base
                assertThat(Department.findByCode("2B")).isNull();

                // On vérifie le nom du département 75
                assertThat(Department.findByCode("75").name).isEqualTo("Paris");

                // On vérifie la région du département des Yvelines
                assertThat(Department.findByCode("78").region.code).isEqualTo("11");

                // Paramètrage avant nouvelle éxécution
                ImportGeographiesDataBatch.clean();
                ImportGeographiesDataBatch.DEPARTMENTS_FILE = "departments.change.nameAndRegion.csv";

                // Exécution du batch
                ImportGeographiesDataBatch.batchRegions();
                ImportGeographiesDataBatch.batchDepartments();
                assertThat(Department.count()).isEqualTo(7);

                // On vérifie que le département 75 a bien changée de nom
                assertThat(Department.findByCode("75").name).isEqualTo("laCapital");

                // On vérifie la région du département des Yvelines a bien changée
                assertThat(Department.findByCode("78").region.code).isEqualTo("22");
            }
        });
        Helpers.stop(app);
        stop(app);
    }

}