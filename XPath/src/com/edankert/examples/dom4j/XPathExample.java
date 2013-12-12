package com.edankert.examples.dom4j;

import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.jaxen.SimpleNamespaceContext;

public class XPathExample {

  public static void main( final String[] args) {
    try {
      final SAXReader reader = new SAXReader();
      final Document document = reader.read("file:catalog.xml");

      final HashMap<String, String> map = new HashMap<String, String>();
      map.put("edx", "http://www.edankert.com/examples/");

      final XPath xpath = DocumentHelper.createXPath("//edx:cd");
      xpath.setNamespaceContext(new SimpleNamespaceContext(map));

      final List nodes = xpath.selectNodes(document);

      for (int i = 0; i < nodes.size(); i++) {
        System.out.println(((Node)nodes.get(i)).getStringValue());
      }

    } catch (final DocumentException e) {
      // the document is not well-formed.
      e.printStackTrace();
    }
  }
}
