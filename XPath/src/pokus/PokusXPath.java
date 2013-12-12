package pokus;

import java.io.IOException;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PokusXPath {

  public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    XPath xpath = XPathFactory.newInstance().newXPath();
    xpath.setNamespaceContext(new NamespaceContext() {

      @Override
      public Iterator getPrefixes(String namespaceURI) {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public String getPrefix(String namespaceURI) {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public String getNamespaceURI(String prefix) {
        System.out.println("Na prefix se pta: '" + prefix + "'");
        if (prefix.equals(""))
          return "http://java.sun.com/xml/ns/javaee";
        // TODO Auto-generated method stub
        return null;
      }
    });

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(false); // never forget this!
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("priklad2-web.xml");

    //String expression = "//*[local-name()='filter']";
    String expression = "//filter-name";
    InputSource inputSource = new InputSource("priklad2-web.xml");
    NodeList nodes = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
    for (int i = 0; i < nodes.getLength(); i++) {
      Node node = nodes.item(i);
      System.out.println(i + "- nodek: " + node.getTextContent());
    }
    String s = xpath.evaluate(expression, inputSource);
    int delka = nodes.getLength();
    System.out.println("DELKA: " + delka);
    System.out.println("S: " + s);
  }
}
