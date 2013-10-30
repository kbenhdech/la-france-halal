package functional.utils;

import org.openqa.selenium.firefox.FirefoxDriver;
import play.libs.F;
import play.test.TestBrowser;
import utils.FakeApplicationConf;

import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Classe utilitaire pour les tests d'intégration.
 *
 * @author Karim BENHDECH
 */
public class CustomizedDriver extends FirefoxDriver {

    private static final CustomizedDriver CUSTOMIZED_DRIVER = new CustomizedDriver();
    /**
     * Port du serveur Selenium.
     * Integer.valueOf(Play.application().configuration().getString("selenium.port"));
     */
    private static final int PORT = 3334;

    public CustomizedDriver() {
        super();
    }

    /**
     * Méthode de surcharge de la méthode running afin de centraliser le port et le driver utilisés.
     *
     * @param callback
     */
    public static void customizedRunning(final F.Callback<TestBrowser> callback) {
        CUSTOMIZED_DRIVER.manage().deleteAllCookies();
        running(testServer(PORT, fakeApplication(FakeApplicationConf.defautlConfiguration())), CUSTOMIZED_DRIVER.getClass(), callback);
    }

    /**
     * Surcharge de goTo et await afin de ne pas répeter le port.
     *
     * @param browser
     * @param url
     * @param awaitPath
     * @param awaitTexte
     */
    public static void customizedGoto(final TestBrowser browser, final String url, final String awaitPath, final String awaitTexte) {
        browser.goTo("http://localhost:" + PORT  + url);
        browser.await().atMost(5, TimeUnit.SECONDS).until(awaitPath).containsText(awaitTexte);
        assertThat(browser.$(awaitPath).first().getText()).contains(awaitTexte);
    }

    /**
     * Surcharge de goTo et await afin de ne pas répeter le port.
     *
     * @param browser
     * @param url
     * @param awaitTexte
     */
    public static void customizedGoto(final TestBrowser browser, final String url, final String awaitTexte) {
        CUSTOMIZED_DRIVER.customizedGoto(browser, url, "h1", awaitTexte);
    }

    /**
     * Surcharge de click et await.
     *
     * @param browser
     * @param path
     * @param pathline
     * @param awaitPath
     * @param awaitPathLine
     * @param awaitTexte
     */
    public static void customizedClick(final TestBrowser browser, final String path, final int pathline, final String awaitPath, final int awaitPathLine, final String awaitTexte) {
        browser.$(path).get(pathline).click();
        browser.await().atMost(5, TimeUnit.SECONDS).until(awaitPath).containsText(awaitTexte);
        assertThat(browser.$(awaitPath).get(awaitPathLine).getText()).contains(awaitTexte);
    }

    /**
     * Surcharge de click et await.
     *
     * @param browser
     * @param path
     * @param pathline
     * @param awaitPath
     * @param awaitTexte
     * @param hasAlert
     */
    public static void customizedClick(final TestBrowser browser, final String path, final int pathline, final String awaitPath, final String awaitTexte, final boolean hasAlert) {
        browser.$(path).get(pathline).click();
        if (hasAlert) {
            browser.getDriver().switchTo().alert().accept();
        }
        browser.await().atMost(5, TimeUnit.SECONDS).until(awaitPath).containsText(awaitTexte);
        assertThat(browser.$(awaitPath).first().getText()).contains(awaitTexte);
    }

    /**
     * Surcharge de click et await.
     *
     * @param browser
     * @param path
     * @param pathline
     * @param awaitPath
     * @param awaitTexte
     */
    public static void customizedClick(final TestBrowser browser, final String path, final int pathline, final String awaitPath, final String awaitTexte) {
        CUSTOMIZED_DRIVER.customizedClick(browser, path, pathline, awaitPath, awaitTexte, false);
    }

    /**
     * Surcharge de click et await.
     *
     * @param browser
     * @param path
     * @param pathline
     * @param awaitTexte
     */
    public static void customizedClick(final TestBrowser browser, final String path, final int pathline, final String awaitTexte) {
        CUSTOMIZED_DRIVER.customizedClick(browser, path, pathline, "h1", awaitTexte, false);
    }

    /**
     * Surcharge de click et await.
     *
     * @param browser
     * @param path
     * @param awaitTexte
     */
    public static void customizedClick(final TestBrowser browser, final String path, final String awaitTexte) {
        CUSTOMIZED_DRIVER.customizedClick(browser, path, 0, "h1", awaitTexte, false);
    }

}
