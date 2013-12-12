This package contains the Example code for the 
"XPath and Default Namespace handling" article.
 
http://www.edankert.com/defaultnamespaces.html

It consists of:

The ./catalog.xml document and 4 Java code examples (in the ./src 
directory)to search the document using DOM, JDOM, dom4j and XOM.

To run these examples, please use the following command-line options:

[DOM]   java -cp xpath-examples.jar com.edankert.examples.dom.XPathExample
[JDOM]  java -cp xpath-examples.jar;lib/jdom.jar;lib/jaxen-1.1.1.jar com.edankert.examples.jdom.XPathExample
[dom4j] java -cp xpath-examples.jar;lib/dom4j-1.6.1.jar;lib/jaxen-1.1.1.jar com.edankert.examples.dom4j.XPathExample
[XOM]   java -cp xpath-examples.jar;lib/xom-1.0.jar;lib/jaxen-1.1.1.jar com.edankert.examples.xom.XPathExample

The archive also contains the example XML Stylesheet (./catalog.xsl). 
To process the XML with the stylesheet please invoke your favorite XML 
Processor from the command-line or use the transform.xhp project 
included in the ./xmlhammer-projects directory.

To be able to process the ./xmlhammer-projects/transform.xhp and the 
also included ./xmlhammer-projects/xpath.xhp project, you will need to 
have the XML Hammer application installed.

This can be downloaded from:
http://www.xmlhammer.org/
