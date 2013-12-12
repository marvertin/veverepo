package com.edankert.examples.xom;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Node;
import nu.xom.ParsingException;

import org.jaxen.JaxenException;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.XPath;
import org.jaxen.xom.XOMXPath;

public class XPathExample {

    public static void main( String[] args) {
		try {
			Builder builder = new Builder();			
			Document document = builder.build("file:catalog.xml");
			
            HashMap<String, String> map = new HashMap<String, String>();
			map.put("edx", "http://www.edankert.com/examples/");
			
			XPath xpath = new XOMXPath("//edx:cd");
			xpath.setNamespaceContext(new SimpleNamespaceContext(map));
			
			List nodes = xpath.selectNodes(document);
			
			for (int i = 0; i < nodes.size(); i++) {
				System.out.println(((Node)nodes.get(i)).getValue());
			}
  
		} catch (JaxenException e) {
			// An error occurred performing the XPath
			e.printStackTrace();
		} catch (IOException e) { 
			// An error occurred opening the document
			e.printStackTrace();
		} catch (ParsingException e) { 
			// An error occurred parsing the document
			e.printStackTrace();
		}
	}
}
