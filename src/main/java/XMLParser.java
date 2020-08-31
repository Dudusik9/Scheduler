import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
    private static final String PATH_TO_CONFIGURE_FILE = "configure.xml";
    private static Map<Integer, Script> mapScripts = new HashMap<>();

    public static Map<Integer, Script> parsingConfigFile()  {
        // Create XML parser
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            XMLHandler xmlHandler = new XMLHandler();
            parser.parse(new File(PATH_TO_CONFIGURE_FILE), xmlHandler);
            return xmlHandler.getMapa();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
