package controllers;

import jsmessages.JsMessages;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;


public class Application extends Controller {

    final static JsMessages messages = new JsMessages(Play.application());

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result jsMessages() {
        return ok(messages.generate("window.Messages")).as("application/javascript");
    }
  
}
