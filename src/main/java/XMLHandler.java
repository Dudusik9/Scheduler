
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;


public class XMLHandler extends DefaultHandler {

    private int id = 0;
    private Script script = new Script();
    private String element;
    private Map<Integer, Script> mapa = new HashMap<>();


    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;

        if(element.equals("script")){
            id = Integer.parseInt(attributes.getValue(0));
            script = new Script();
            script.setId(id);
            mapa.put(id, script);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
            switch (element) {
                case "name" :
                    mapa.get(id).setPathToFile(new String(ch, start, length));
                    break;
                case "startTime":
                    mapa.get(id).setLaunchTime(new String(ch, start, length));
                    break;
                case "launchDay":
                    mapa.get(id).setLaunchDay(new String(ch, start, length));
                    break;
                default:
                    break;
            }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        mapa.put(id, script);
        element = "";
    }

    @Override
    public void endDocument() throws SAXException {
    }

    public Map<Integer, Script> getMapa() {
        return mapa;
    }
}
