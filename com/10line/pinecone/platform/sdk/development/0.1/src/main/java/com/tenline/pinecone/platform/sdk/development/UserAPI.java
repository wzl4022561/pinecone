/**
 * 
 */
package com.tenline.pinecone.platform.sdk.development;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;

import com.google.api.client.http.HttpMethod;
import com.tenline.pinecone.platform.model.User;

/**
 * @author Bill
 *
 */
public class UserAPI extends JaxbAPI {
	
	/**
	 * 
	 * @param host
	 * @param port
	 * @param context
	 */
	public UserAPI(String host, String port, String context) {
		super(host, port, context);
		// TODO Auto-generated constructor stub
		try {
			jaxbContext = JAXBContext.newInstance(User.class);
			marshaller = jaxbContext.createMarshaller();
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param filter
	 * @param consumerKey
	 * @param consumerSecret
	 * @param token
	 * @param tokenSecret
	 * @return
	 * @throws Exception
	 */
	public APIResponse show(String filter, String consumerKey, String consumerSecret, 
			String token, String tokenSecret) throws Exception {
		APIResponse response = new APIResponse();
		String requestUrl = url + "/api/user/show/" + filter;
		connection = (HttpURLConnection) new URL(requestUrl).openConnection();
		connection.setRequestProperty("Authorization", getAuthorization(requestUrl, HttpMethod.GET.name(), 
				consumerKey, consumerSecret, token, tokenSecret));
		connection.setConnectTimeout(TIMEOUT);
		connection.connect();
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			JSONArray array = new JSONArray(new String(new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8")).readLine()));
			Collection<User> message = new ArrayList<User>();
			for (int i=0; i<array.length(); i++) {
				message.add((User) unmarshaller.unmarshal(new MappedXMLStreamReader(array.getJSONObject(i), 
							new MappedNamespaceConvention(new Configuration()))));
			}
			response.setDone(true);
			response.setMessage(message);
			connection.getInputStream().close();
		} else {
			response.setDone(false);
			response.setMessage("Show User Error Code: Http (" + connection.getResponseCode() + ")");
		}
		connection.disconnect();
		return response;
	}

}
