import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão<br>
 * IPP - Instituto Politécnico do Porto <br>
 * LEI - Licenciatura em Engenharia Informática<br>
 * PEI - Processamento Estruturado de Informação<br>
 * 2017 / 2018 <br>
 * </h3>
 * <p>
 * <strong>Descrição:</strong>
 * Classe de exemplo que permite validar a sintaxe de um documento XML
 * <br>
 * </p>
 *
 */

public class XMLManagement {
    private final String xmlFile, xsdFile;
    private Document document;

    /**
     *
     * @param xmlFile Documento XML a processar
     * @param xsdFile Documento XSD a processar
     */
    public XMLManagement(String xmlFile, String xsdFile) {
        this.xmlFile = xmlFile;
        this.xsdFile=xsdFile;
    }

    /**
     * Método responsável por ler (parse) um documento XML
     *
     * @param logOnError permite ativar/desativas as mensagens de erro que
     * possam surgir através da linha de comandos
     * @return valor booleano sinalizando sucesso/insucesso da operação
     */
    public boolean read(boolean logOnError) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(new XMLErrorHandler());
            dbf.setIgnoringComments(true);
            dbf.setIgnoringElementContentWhitespace(true);
            document = db.parse(this.xmlFile);
            document.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            if (logOnError) {
                Logger.getLogger(XMLManagement.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }
            return false;
        }
        return true;
    }
    
     /**
     * Método responsável por validar um documento XML com o seu schema
     *
     * @param xml Documento XML a processar
     * @param xsd Documento XSD a processar
     * @param logOnError permite ativar/desativas as mensagens de erro que
     * possam surgir através da linha de comandos
     * @return valor booleano sinalizando sucesso/insucesso da operação
     */
    public static boolean validate(File xml, File xsd, boolean logOnError) {
        Source schemaFile = new StreamSource(xsd), xmlFile = new StreamSource(xml);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            schemaFactory.setErrorHandler(new XSDErrorHandler());
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.setErrorHandler(new XSDErrorHandler());
            validator.validate(xmlFile);
        } catch (SAXException | IOException ex) {
            if (logOnError) {
                Logger.getLogger(XMLManagement.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }
            return false;
        }
        return true;
    }
    
       /**
     * Método que permite validar um documento XML previamento definido com o
     * seu schema também previamente definido. Sem a existência de um xsd
     * retorna "true"
     *
     * @param output permite ativar/desativas as mensagens de erro que possam
     * surgir através da linha de comandos
     * @return
     */
    public boolean validate(boolean output) {
        return xsdFile == null || xsdFile.isEmpty() ? true : XMLManagement.validate(new File(xmlFile), new File(xsdFile), output);
    }
        
}
