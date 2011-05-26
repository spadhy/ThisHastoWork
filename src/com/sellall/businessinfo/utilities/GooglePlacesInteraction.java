package com.sellall.businessinfo.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class GooglePlacesInteraction {
	private static String PLACES_URL = "GOOGLE_PLACES_URL";
	private static String DATA_FORMAT = "DATA_FORMAT_FROM_GOOGLE_PLACES";
	private static String RADIUS = "RADIUS_GOOGLE_PLACES";
	private static String SENSOR = "SENSOR_GOOGLE_PLACES";
	private static String API_KEY = "GOOGLE_API_KEY";
	@SuppressWarnings("rawtypes")
	static HashMap CONFIGVALUES = new HashMap();
	@SuppressWarnings("finally")
	public List<String> getLatLongStrings() throws Exception {
		getConfigValues();
		String inputXML = "";
		System.out.println("Display config values-->" + CONFIGVALUES.get("DONT_USE_GAPI").toString());
		if (CONFIGVALUES.get("DONT_USE_GAPI").toString().equals("Y")){
			inputXML = readFile("E:\\workspaceAndroid\\Spring3HibernateMaven\\src\\main\\java\\com\\sellall\\businessinfo\\utilities\\persons.xml");
			System.out.println("Inside the if clause");
		} else {
			inputXML = getPlacesFromGooglePlaces();
		}
		

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		InputSource source = new InputSource(new StringReader(inputXML));
		Document document = factory.newDocumentBuilder().parse(source);
		List<String> latLongList = new ArrayList<String>();
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile("count(//result)");
			// Run the query and get the number of nodes
			Double number = (Double) expr.evaluate(document, XPathConstants.NUMBER);
			
			for (int i = 1; i <= number; i++) {
				StringBuilder latLongString = new StringBuilder();				
				expr = xpath.compile("/PlaceSearchResponse/result[" + i + "]/geometry/location/lat");
				latLongString.append(expr.evaluate(document, XPathConstants.STRING).toString() + "-");
				expr = xpath.compile("/PlaceSearchResponse/result[" + i + "]/geometry/location/lng");				
				latLongString.append(expr.evaluate(document, XPathConstants.STRING).toString());
				latLongList.add(latLongString.toString());
			}
			return latLongList;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			return latLongList;
		}
	}

	public String getPlacesFromGooglePlaces() throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		String url = buildParameterForGooglePlaces();
		String result = null;
		try {
			HttpGet httpget = new HttpGet(url);
			// Execute the request
			HttpResponse response;
			InputStream instream = null;
			response = httpclient.execute(httpget);
			// Examine the response status
			// Get hold of the response entity
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to worry about connection release
			if (entity != null) {
				// A Simple XML Response Read
				instream = entity.getContent();
				result = streamToString(instream);
			}
		} catch (Exception ex) {
			System.out.println("Exception message -->" + ex.getMessage());
		}

		// String xmlObj =
		// service.accept(MediaType.APPLICATION_XML).get(String.class);
		return result;
	}

	private static String buildParameterForGooglePlaces() throws Exception {
		// TODO latLong hardcoded, types hardcoded change it to real location
		// and real types... 
		//TODO change implementation to use static hashmap
		if (CONFIGVALUES == null || CONFIGVALUES.size() == 0)
			getConfigValues();
		
		String latLong = "39.9675714,-75.5321234";
		StringBuilder sb = new StringBuilder();
		sb.append(CONFIGVALUES.get(PLACES_URL) + "/");
		sb.append(CONFIGVALUES.get(DATA_FORMAT) + "?");
		sb.append("location=" + latLong);
		sb.append("&radius=" + CONFIGVALUES.get(RADIUS));
		sb.append("&types=" + "food");
		sb.append("&name=" + "pizza");
		sb.append("&sensor=" + CONFIGVALUES.get(SENSOR));
		sb.append("&key=" + CONFIGVALUES.get(API_KEY));
		return sb.toString();
	}

	private static String streamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	@SuppressWarnings("static-access")
	private static void getConfigValues(){
		LoadConfig loadConfig = new LoadConfig();
		CONFIGVALUES=loadConfig.retrieve();  
	}
	
	private static String readFile(String path) throws IOException {
		  FileInputStream stream = new FileInputStream(new File(path));
		  try {
		    FileChannel fc = stream.getChannel();
		    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		    return Charset.defaultCharset().decode(bb).toString();
		  }
		  finally {
		    stream.close();
		  }
		}	
}
