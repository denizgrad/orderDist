package com.du.order.dist.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.du.order.dist.client.model.AsliBorekSiparisIn;
import com.du.order.dist.client.model.CreateAsliBorekSiparisIn;
import com.du.order.dist.client.model.UpdateAsliBorekSiparisIn;
import com.google.gson.Gson;

public class AsliBorekClient {
	private String host;
	private String userName;
	private String password;
/**
 * @param host restService's host. /createSiparis or /updateSiparis will be added in case
 * @param userName sf username defined for authentication
 * @param password sf password defined for authentication
 */
	public AsliBorekClient(String host, String userName, String password) {
		this.host = host;
		this.userName = userName;
		this.password = password;
	}
	
	public HttpResponse createSiparis(CreateAsliBorekSiparisIn createSiparis) throws Exception{
		createSiparis.setUserName(getUserName());
		createSiparis.setPassword(getPassword());
		return sendPost(createSiparis, getCreateUrl());
	}
	
	public HttpResponse updateSiparis(UpdateAsliBorekSiparisIn updateSiparis) throws Exception{
		updateSiparis.setUserName(getUserName());
		updateSiparis.setPassword(getPassword());
		return sendPost(updateSiparis, getUpdateUrl());
	}
	
	private HttpResponse sendPost(AsliBorekSiparisIn siparis, String postUrl) throws Exception {

		Gson         gson          = new Gson();
		HttpClient   httpClient    = HttpClientBuilder.create().build();
		HttpPost     post          = new HttpPost(postUrl);
		StringEntity postingString = new StringEntity(gson.toJson(siparis));
		
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json");
		HttpResponse  response = httpClient.execute(post);
		return response;
	}
	
	private String getCreateUrl(){
		return this.getHost()+"/order.dist/v1/siparis/islem/createSiparis";
	}
	private String getUpdateUrl(){
		return this.getHost()+"/order.dist/v1/siparis/islem/updateSiparis";
	}
	
	//getters setters
	public String getUserName() {
		return userName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
