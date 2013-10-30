package batch;

import models.geography.City;
import org.junit.Test;
import play.test.FakeApplication;
import play.test.Helpers;
import utils.FakeApplicationConf;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Tests de récupération des département.
 *
 * @author Karim BENHDECH
 */
public class CityBatchTest {

    @Test
    public void execute() {
        FakeApplication app = fakeApplication(FakeApplicationConf.defautlConfiguration());

        running(app, new Runnable() {
            public void run() {
                ImportGeographiesDataBatch.SOURCE_DIRECTORY = "test/batch/resources/";
                ImportGeographiesDataBatch.REGIONS_FILE = "regions.origin.csv";
                ImportGeographiesDataBatch.DEPARTMENTS_FILE = "departments.origin.csv";
                ImportGeographiesDataBatch.CODES_FILE = "correspondances.origin.csv";

                // Paramètrage avant éxécution
                ImportGeographiesDataBatch.clean();
                ImportGeographiesDataBatch.CITIES_FILE = "cities.origin.csv";

                // Exécution du batch
                // On test que les 8 départements sont bien récupérées
                // La haute corse ne doit pas l'être car sa région n'est pas en base
                assertThat(City.count()).isEqualTo(0);
                ImportGeographiesDataBatch.execute();
                assertThat(City.count()).isEqualTo(3);

                // Soissons ne doit pas être présente car sa région n'est pas en base
                assertThat(City.findByCode("22", "02", "722")).isNull();

                // Evry ne doit pas être présente car son département n'est pas en base
                assertThat(City.findByCode("11", "91", "228")).isNull();

                // On vérifie le nom de la ville  75056
                assertThat(City.findByCode("11", "75" , "100").name).isEqualTo("Paris");
                // On vérifie le code postal de la ville  75056
                assertThat(City.findByCode("11", "75" , "100").zipCode).isEqualTo("75000");

                // Paramètrage avant nouvelle éxécution
                ImportGeographiesDataBatch.clean();
                ImportGeographiesDataBatch.CITIES_FILE = "cities.change.NameAndDepartmentAndInseeCode.csv";

                // Exécution du batch
                // Le changement de département provoque l'ajout d'une ligne quand le département existe
                // Le changement de code INSEE provoque l'ajout d'une ligne
                ImportGeographiesDataBatch.execute();
                assertThat(City.count()).isEqualTo(4);

                // On vérifie que laville 75056 a bien changé de nom
                assertThat(City.findByCode("11", "75" , "100").name).isEqualTo("laCapital");
            }
        });
        Helpers.stop(app);
        stop(app);
    }

}