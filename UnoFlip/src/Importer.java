import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Importer extends DefaultHandler {

    private ArrayList<Player> players;

    Game game;
    private StringBuilder elementContent;

    @Override
    public void startDocument(){
        players = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if(qName.equalsIgnoreCase("Player")){
            Player player;
            if (attributes.getValue("bot").equals("false")){
                player = new Player(attributes.getValue("name"));
            }
            else {
                player = new AIPlayer(attributes.getValue("name"));
            }
            player.addPoints(Integer.parseInt(attributes.getValue("score")));
            System.out.println(player);
            game.addPlayer(player);
            System.out.println(game.hasPlayers());
        }
        elementContent = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length){
        elementContent.append(ch, start, length);
    }

    /*
    @Override
    public void endElement(String uri, String localName, String qName){
        if(qName.equalsIgnoreCase("ID")){
            employees.get(employees.size()-1).setID(Integer.parseInt(elementContent.toString()));
        }else if(qName.equalsIgnoreCase("name")){
            employees.get(employees.size()-1).setName(elementContent.toString());
        }else if(qName.equalsIgnoreCase("department")){
            employees.get(employees.size()-1).setDepartment(elementContent.toString());
        }else if(qName.equalsIgnoreCase("homeNumber")) {
            int homeNo = Integer.parseInt(elementContent.toString());
            employees.get(employees.size()-1).getAddress().setHomeNo(homeNo);
        } else if(qName.equalsIgnoreCase("street")) {
            String street = elementContent.toString();
            employees.get(employees.size()-1).getAddress().setStreet(street);
        }else if(qName.equalsIgnoreCase("city")) {
            String city = elementContent.toString();
            employees.get(employees.size()-1).getAddress().setCity(city);
        }
    }

    */

    public void readXMLGameFile(String fileName, Game game) throws IOException {
        this.game = game;
        System.out.println(fileName);
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            File file = new File(fileName);
            parser.parse(file, this);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new IOException(e);
        }
    }
}
