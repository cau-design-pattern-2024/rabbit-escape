package rabbitescape.ui.swing;

import static rabbitescape.engine.i18n.Translation.*;
import static rabbitescape.engine.util.NamedFieldFormatter.*;

import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import rabbitescape.engine.World;
import rabbitescape.engine.util.Util;

public class DialogText
{
    /**
     * Length of line before wrapping.
     */
    public static final int lineLength = 50;

    static String descriptionHtml( World world )
    {
        return
              "<p>"
            + Util.join( "<br/>",
                         Util.wrap( t( world.description ), lineLength ) )
            + "</p>";
    }

    static String statsHtml(World world) {
        Map<String, Object> statsValues = DialogText.statsValues(world);

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<p class='lower'>");
        htmlBuilder.append(
            t(
                "Rabbits: ${num_rabbits}",
                statsValues
            )
        );

        // num_weak_rabbits가 0이 아닌 경우 텍스트 추가
        if ((int) statsValues.get("num_weak_rabbits") > 0) {
            htmlBuilder.append(" ");
            htmlBuilder.append(
                t(
                    "Weak Rabbits: ${num_weak_rabbits}",
                    statsValues
                )
            );
        }

        htmlBuilder.append(" ");
        htmlBuilder.append(
            t(
                "Must save: ${num_to_save}",
                statsValues
            )
        );

        htmlBuilder.append("</p>");
        return htmlBuilder.toString();
    }

    static String authorHtml( World world )
    {
        if ( Util.isEmpty( world.author_name ) )
        {
            return "";
        }

        String ret = "<p class='lower'>";

        if ( Util.isEmpty( world.author_url ) )
        {
            ret += t(
                "by ${author_name}",
                Util.newMap( "author_name", world.author_name )
            );
        }
        else
        {
            ret += t(
                "by <a href='${author_url}'>${author_name}</a>",
                Util.newMap(
                    "author_name", world.author_name,
                    "author_url", world.author_url
                )
            );
        }

        ret += "</p>";

        return ret;
    }

    static Component introText(
        Component parent, World world )
    {
        UIDefaults defaults = UIManager.getDefaults();
        Font font = defaults.getFont( "Label.font" );

        String text = format(
              "<style>"
            + "p"
            + "{"
            + "    font-family: ${font-family};"
            + "    font-size: ${font-size};"
            + "    font-weight: ${font-weight};"
            + "    padding: 0px;"
            + "    margin: 0px;"
            + "}"
            + "p.lower"
            + "{"
            + "    margin-top: 10px;"
            + "    font-weight: lighter;"
            + "}"
            + "</style>"
            + "${description}"
            + "${stats}"
            + "${author}",
            Util.newMap(
                "font-family", font.getName(),
                "font-size", Integer.toString( font.getSize() ),
                "font-weight", font.getStyle() == Font.BOLD ? "bold" : "normal",
                "description", descriptionHtml( world ),
                "stats", statsHtml( world ),
                "author", authorHtml( world )
            )
        );

        JEditorPane ret = new JEditorPane();
        ret.setEditable( false );
        ret.setBackground( null );
        ret.setContentType( "text/html" );
        ret.setText( text );
        ret.addHyperlinkListener( new OpenLinkInDesktopBrowser( parent ) );

        return ret;
    }

    static Map<String, Object> statsValues( World world )
    {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put( "num_rabbits", world.num_rabbits );
        values.put( "num_weak_rabbits", world.num_weak_rabbits );
        values.put( "num_to_save", world.num_to_save );
        values.put( "num_saved",   world.num_saved );
        return values;
    }

}
