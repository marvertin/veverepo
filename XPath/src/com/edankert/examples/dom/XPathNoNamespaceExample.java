package com.edankert.examples.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XPathNoNamespaceExample {

  public static void main(final String[] args) {
    try {
      final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
      domFactory.setNamespaceAware(false);

      final DocumentBuilder builder = domFactory.newDocumentBuilder();

      final Document document = builder.parse(new InputSource("file:catalog.xml"));
      System.out.println("------------------------");
      Vypisovac.print(document, 0);

      final XPathFactory factory = XPathFactory.newInstance();
      final XPath xpath = factory.newXPath();

      final Object nodes = xpath.evaluate("//title", document.getDocumentElement(), XPathConstants.NODESET);

      System.out.println("------------------------");
      if (nodes instanceof NodeList) {
        for (int i = 0; i < ((NodeList)nodes).getLength(); i++) {
          System.out.println(((NodeList)nodes).item( i).getTextContent());
        }
      }

    } catch (final ParserConfigurationException e) {
      e.printStackTrace();
    } catch (final XPathExpressionException e) {
      e.printStackTrace();
    } catch (final SAXException e) {
      e.printStackTrace();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

}