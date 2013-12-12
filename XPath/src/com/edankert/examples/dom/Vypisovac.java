package com.edankert.examples.dom;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Vypisovac {

  public static void print(final Node aNode, final int level) {
    if (aNode instanceof Element) {
      final Element ele = (Element) aNode;
      System.out.printf("%" + level*4 + "s%s - %s - %s | %s - %s%n", "", ele.getPrefix(), ele.getLocalName(), ele.getNamespaceURI(), ele.getTagName(), ele.getNodeName());
    }
    System.out.flush();
    final NodeList nodes = aNode.getChildNodes();
    for (int i = 0; i < nodes.getLength(); i++) {
      final Node node = nodes.item( i);
      print(node, level + 1);
    }
  }


}
