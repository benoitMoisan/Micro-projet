package mailBox;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class MailBoxClientRest {
	
	static final String REST_URI = "http://localhost:9999/MyServer";
	
	public static void main(String[] args) {
		Client client = Client.create(new DefaultClientConfig());
		URI uri=UriBuilder.fromUri(REST_URI).build();
		WebResource service = client.resource(uri);
		
		System.out.println("addUser : yang");
		service.path("micro/addUser").put("Yang");
		System.out.println("---------------------------------------------------");


		
	}
}
