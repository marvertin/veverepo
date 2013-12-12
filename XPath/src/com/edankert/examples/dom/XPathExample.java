package com.edankert.examples.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.NamespaceContext;
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

public class XPathExample {

  public static void main(final String[] args) {
    try {
      final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
      domFactory.setNamespaceAware(true);

      final DocumentBuilder builder = domFactory.newDocumentBuilder();

      final Document document = builder.parse(new InputSource("file:catalog.xml"));

      System.out.println("------------------------");
      Vypisovac.print(document, 0);
      final XPathFactory factory = XPathFactory.newInstance();
      final XPath xpath = factory.newXPath();
      xpath.setNamespaceContext(new NamespaceContext() {
        @Override
        public String getNamespaceURI(final String prefix) {
          if (prefix.equals("edx")) {
            return "http://www.edankert.com/examples/";
          }

          return "";
        }

        @Override
        public String getPrefix(final String namespaceURI) {
          if (namespaceURI.equals("http://www.edankert.com/examples/")) {
            return "edx";
          }

          return "";
        }

        @Override
        public Iterator getPrefixes(final String namespaceURI) {
          final List<String> list = new ArrayList<String>();

          if (namespaceURI.equals("http://www.edankert.com/examples/")) {
            list.add("edx");
          }

          return list.iterator();
        }
      });

      final Object nodes = xpath.evaluate("//edx:title", document.getDocumentElement(), XPathConstants.NODESET);

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