package batch;

import org.fest.assertions.Condition;
import org.junit.Test;
import play.test.FakeApplication;
import play.test.Helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Classes de tests du temps réel de traitement.
 * La purge est également testée içi..
 *
 * @author Karim BENHDECH
 */
public class BatchTimeTest {

    @Test
    public void tempsExecution() {
        Map<String, String> conf = new HashMap<String, String>();
        //conf.put("ome.directory", "./test/resources/OME/vraisDonnees/");
        //conf.put("db.default.url", "jdbc:h2:mem:play:~/scale");
        FakeApplication app = fakeApplication(conf);
        running(app, new Runnable() {
            public void run() {
                Date date1 = new Date();

                ImportGeographiesDataBatch.execute();

                Date date2 = new Date();
                Long duration = (date2.getTime() - date1.getTime()) / 1000;

                assertThat(duration).is(new Condition<Long>() {
                    @Override
                    public boolean matches(Long duration) {
                        return duration <= 20;
                    }
                });
            }
        });
        Helpers.stop(app);
        stop(app);
    }

}