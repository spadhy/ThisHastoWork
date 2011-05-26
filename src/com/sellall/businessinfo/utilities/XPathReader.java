package com.sellall.businessinfo.utilities;

import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XPathReader {

	private Document xmlDocument;
	private XPath xPath;

	public  void initObjects(InputSource is) {
		try {
			xmlDocument = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(is);
			xPath = XPathFactory.newInstance().newXPath();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (SAXException ex) {
			ex.printStackTrace();
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		}
	}

	public Object read(String expression, QName returnType) {
		try {
			XPathExpression xPathExpression = xPath.compile(expression);
			return xPathExpression.evaluate(xmlDocument, returnType);
		} catch (XPathExpressionException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}