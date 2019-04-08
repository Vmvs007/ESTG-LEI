
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
 * Classe que implementa a interface {@link org.xml.sax.ErrorHandler ErrorHandler} 
 * para representar os erros que podem ocorrer durante o processo de validação de um
 * documento XML com um schema
 * <br>
 * </p>
 *
 */
public class XSDErrorHandler implements ErrorHandler{

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("Warning: " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
        throw new SAXException();
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
        throw new SAXException();
    }

}
