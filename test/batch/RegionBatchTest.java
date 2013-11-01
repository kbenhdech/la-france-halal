package batch;

import models.geography.Region;
import org.junit.Test;
import play.test.FakeApplication;
import play.test.Helpers;
import utils.FakeApplicationConf;
import utils.YamlDataType;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Tests de récupération des régions.
 *
 * @author Karim BENHDECH
 */
public class RegionBatchTest {

    @Test
    public void execute() {
        FakeApplication app = fakeApplication(FakeApplicationConf.defautlConfiguration(YamlDataType.BATCH));

        running(app, new Runnable() {
            public void run() {
                ImportGeographiesDataBatch.SOURCE_DIRECTORY = "test/batch/resources/";

                // Paramètrage avant éxécution
                ImportGeographiesDataBatch.clean();
                ImportGeographiesDataBatch.REGIONS_FILE = "regions.origin.csv";

                // On test que les 3 régions sont bien récupérées
                assertThat(Region.count()).isEqualTo(0);
                ImportGeographiesDataBatch.batchRegions(); // Exécution du batch
                assertThat(Region.count()).isEqualTo(3);

                // On vérifie le nom de la région 11
                assertThat(Region.findByCode("11").name).isEqualTo("Île-de-France");

                // Paramètrage avant nouvelle éxécution
                ImportGeographiesDataBatch.clean();
                ImportGeographiesDataBatch.REGIONS_FILE = "regions.change.name.csv";

                // On vérifie que la région 11 a bien changée de nom
                ImportGeographiesDataBatch.batchRegions(); // Exécution du batch
                assertThat(Region.count()).isEqualTo(3);
                assertThat(Region.findByCode("11").name).isEqualTo("IDF");
            }
        });
        Helpers.stop(app);
        stop(app);
    }

}