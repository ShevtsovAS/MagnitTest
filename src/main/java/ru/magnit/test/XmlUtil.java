package main.java.ru.magnit.test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class XmlUtil {

    private XmlUtil() {}

    public static void convertWithXSLT(String fromFile, String toFile, String xsltFile) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer(new StreamSource(xsltFile));
        transformer.transform(new StreamSource(fromFile), new StreamResult(toFile));

        System.out.printf("Conversion from file %s to file %s with style %s completed!\n", fromFile, toFile, xsltFile);
    }

    public static long countSumOfElements(String xmlFile) throws ParserConfigurationException, IOException, SAXException {
        File fXmlFile = new File(xmlFile);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(fXmlFile);

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("entry");

        long result = 0;
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            Element element = (Element) node;
            result += Long.parseLong(element.getAttribute("field"));
        }
        
        return result;
    }
}
