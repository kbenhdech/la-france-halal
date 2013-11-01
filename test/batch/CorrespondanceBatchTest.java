package batch;

import org.junit.Test;
import play.test.FakeApplication;
import play.test.Helpers;
import utils.FakeApplicationConf;
import utils.YamlDataType;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Tests de récupération des correspondances entre code INSEEE et code postal.
 *
 * @author Karim BENHDECH
 */
public class CorrespondanceBatchTest {

    @Test
    public void execute() {
        FakeApplication app = fakeApplication(FakeApplicationConf.defautlConfiguration(YamlDataType.BATCH));

        running(app, new Runnable() {
            public void run() {
                ImportGeographiesDataBatch.SOURCE_DIRECTORY = "test/batch/resources/";

                // Paramètrage avant éxécution
                ImportGeographiesDataBatch.clean();
                ImportGeographiesDataBatch.CODES_FILE = "correspondances.origin.csv";

                // On test que les 7 correspondances sont bien récupérées
                assertThat(ImportGeographiesDataBatch.citiesCode.size()).isEqualTo(0);
                ImportGeographiesDataBatch.batchCitiesCode(); // Exécution du batch
                assertThat(ImportGeographiesDataBatch.citiesCode.size()).isEqualTo(7);

                // l'absence de zipCode ou de codePostal ne peut pas être testé ==> ERREUR

                // On vérifie que chaque zipCode et chaque codePostal de la hashMap a bien 5 caractères
                for (Map.Entry<String, String> entry : ImportGeographiesDataBatch.citiesCode.entrySet()) {
                    assertThat(entry.getKey().length()).isEqualTo(5);
                    assertThat(entry.getValue().length()).isEqualTo(5);
                }
            }
        });
        Helpers.stop(app);
        stop(app);
    }

}