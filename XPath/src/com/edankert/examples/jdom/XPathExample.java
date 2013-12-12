package com.edankert.examples.jdom;

import java.io.IOException;
import java.util.List;

import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XPathExample {

  public static void main(final String[] args) {
    try {
      final SAXBuilder builder = new SAXBuilder();

      final Document document = builder.build("file:catalog.xml");


      final XPath xpath = new JDOMXPath("//edx:title");
      xpath.addNamespace("edx", "http://www.edankert.com/examples/");

      final List nodes = xpath.selectNodes(document);

      for (int i = 0; i < nodes.size(); i++) {
        System.out.println( ((Content)nodes.get(i)).getValue());
      }

    } catch (final JaxenException e) {
      // An error occurred performing the XPath
      e.printStackTrace();
    } catch (final IOException e) {
      // An error occurred opening the document
      e.printStackTrace();
    } catch (final JDOMException e) {
      // An error occurred parsing the document
      e.printStackTrace();
    }
  }
}
