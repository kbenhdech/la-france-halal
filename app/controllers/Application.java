package controllers;

import jsmessages.JsMessages;
import play.Play;
import play.Routes;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * Controller principal de l'application.
  */
public class Application extends Controller {

    /**
     * Pour l'i18n.
     */
    final static JsMessages messages = new JsMessages(Play.application());

    /**
     * Page principale.
     *
     * @return Result
     */
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    /**
     * Router javascript pour les messages.
     * *
     * @return Result
     */
    public static Result jsMessages() {
        return ok(messages.generate("window.Messages")).as("application/javascript");
    }

    /**
     * Router javascript pour les APIs.
     * *
     * @return Result
     */
    public static Result jsApis() {
        response().setContentType("text/javascript");
        return ok(
                Routes.javascriptRouter("jsRoutes",
                        controllers.api.routes.javascript.AddressesApi.findByTerm(),
                        controllers.api.routes.javascript.AddressesApi.findById()
                )
        );
    }
  
}
