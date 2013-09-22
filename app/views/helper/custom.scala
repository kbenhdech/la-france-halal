import scala.language.implicitConversions

package views.html.helper {

import java.util.Date
import play.templates.TemplateMagic.RichDate


/**
 * Composant pour afficher une valeur
 * - soit String
 * - soit Date avec formatage
 * - soit Object avec appel à toString.
 * Si x est null, String vide.
 *
 * @author Lang Hoang
 * @param x : valeur(objet) à afficher
 * @param formatPattern: motif de formatage de la date, facultatif ("dd/MM/yyyy") par défaut
 *
 * ex :
 * @display("TOTO")     -> TOTO
 * @display(new Date()) -> 26/07/2013
 * @display(new Date(), "dd/MM") -> 26/07
 * @display(null) -> ""
 */
object display {

    def apply(x: Any, formatPattern:String ="dd/MM/yyyy"  ) = x match {
      case null => ""
      case x:String => x
      case x:Date => new RichDate(x) format(formatPattern)
      case true => "oui"
      case false => "non"
      case _ => x.toString
    }

  }

}

