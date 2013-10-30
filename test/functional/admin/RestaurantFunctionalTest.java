package functional.admin;

import functional.builders.RestaurantFunctionalBuilder;
import org.junit.Test;
import play.libs.F;
import play.test.TestBrowser;

import static functional.utils.CustomizedDriver.customizedRunning;

/**
 * @author Karim BENHDECH
 */
public class RestaurantFunctionalTest {

    @Test
    public void deleteReferenced() {
        customizedRunning(new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                String restoName = "Houuuu";
                // On créé le restaurant
                RestaurantFunctionalBuilder.create(browser, restoName);

            }
        });
    }
}
