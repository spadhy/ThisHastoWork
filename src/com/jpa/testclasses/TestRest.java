package com.jpa.testclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.UriBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestRest {
	public static void main(String[] args) throws URISyntaxException, HttpException, IOException {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		// Get XML
		//System.out.println(service.path("rest").path("businesslist").accept(MediaType.TEXT_XML).get(String.class));
		// Get XML for application
		String url = "http://192.168.1.22:8080/ThisHastoWork/"; 
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url); 
        // prepare parameters 
        HttpParams params = new BasicHttpParams(); 
        params.setParameter("locationJSON", "39.9658400--75.5510930"); 
        httpget.setParams(params);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        // If the response does not enclose an entity, there is no need to worry about connection release
        if (entity != null) {
        	InputStream instream = entity.getContent();
            String result= streamToString(instream);
            System.out.println(result.toString());
        }

//		System.out.println(service.path("rest").path("businessinfolist").accept(
				//MediaType.APPLICATION_XML).get(String.class));
		// Get JSON for application
		//System.out.println(service.path("rest").path("businesslist").accept(MediaType.APPLICATION_XML).get(String.class));
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

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://192.168.1.22:8080/ThisHastoWork/").build();
	}

}