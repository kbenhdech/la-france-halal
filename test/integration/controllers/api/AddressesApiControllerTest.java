package integration.controllers.api;

import controllers.api.routes;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.mvc.Result;
import utils.FakeApplicationConf;
import utils.FakeApplicationTestSuite;
import utils.YamlDataType;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Tests d'intégration des APIs relatives aux adresses.
 */
public class AddressesApiControllerTest {

    @BeforeClass
    public static void start() {
        Map<String, String> conf = FakeApplicationConf.defautlConfiguration(YamlDataType.API_ADDRESSES);
        FakeApplicationTestSuite.startApp(conf);
    }

    @AfterClass
    public static void stop() {
        FakeApplicationTestSuite.stopApp();
    }

    @Test
    public void callFindByIdWithNotExistValue() {
        Result result = callAction(routes.ref.AddressesApi.findById(0L));
        assertThat(status(result)).isEqualTo(NOT_FOUND);
    }

    @Test
    public void callFindByIdWithExistValue() {
        Result result = callAction(routes.ref.AddressesApi.findById(1L));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("application/json");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("Sartrouville");
    }

    @Test
    public void callFindByTermWithNullValue() {
        Result result = callAction(routes.ref.AddressesApi.findByTerm(null));
        assertThat(status(result)).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void callFindByTermWithEmptyValue() {
        Result result = callAction(routes.ref.AddressesApi.findByTerm(""));
        assertThat(status(result)).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void callFindByTermWithTooShortValue() {
        Result result = callAction(routes.ref.AddressesApi.findByTerm("Sa"));
        assertThat(status(result)).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void callFindByTermWithNotExistResul() {
        Result result = callAction(routes.ref.AddressesApi.findByTerm("uneVilleQuiExistePas"));
        assertThat(status(result)).isEqualTo(NOT_FOUND);
    }

    @Test
    public void callFindByTermWithNameAndManyResul() {
        Result result = callAction(routes.ref.AddressesApi.findByTerm("Sart"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("application/json");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("Sartrouville");
    }

    @Test
    public void callFindByTermWithZipCodeAndManyResul() {
        Result result = callAction(routes.ref.AddressesApi.findByTerm("785"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("application/json");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("Sartrouville");
    }
}
