import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import java.util.logging.*;

public class GestaoXML extends XMLManagement {
    private String filexml,filexsd;
    private Document doc;


    public GestaoXML(String filexml,String filexsd) {
        super(filexml,filexsd);
        this.filexml=filexml;
        this.filexsd=filexsd;

    }

    public void readXML() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();

            doc = db.parse(new File(filexml));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(GestaoXML.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void printMenuXML() {
        System.out.println("Menu :" + doc.getDocumentElement().getNodeName());

        NodeList pizzas = doc.getChildNodes().item(0).getChildNodes();
        int h=1;
        for (int i = 0; i < pizzas.getLength(); i++) {
            if (pizzas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element pizzaElement = (Element) pizzas.item(i);
                System.out.println("Ingredient n." + h++ );
                System.out.println("Name : " + pizzaElement.getElementsByTagName("name").item(0).getTextContent());
                System.out.println("Category : " + pizzaElement.getElementsByTagName("category").item(0).getTextContent());
                System.out.println("Unit : " + pizzaElement.getElementsByTagName("unit").item(0).getTextContent());
                System.out.println("Calories : " + pizzaElement.getElementsByTagName("calories").item(0).getTextContent());
                System.out.println();
            }
        }
    }
}
