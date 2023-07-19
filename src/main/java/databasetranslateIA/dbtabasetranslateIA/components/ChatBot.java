package databasetranslateIA.dbtabasetranslateIA.components;

import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.apache.http.HttpEntity;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;

@Component
public class ChatBot {
	@Value("${api.endpoint}")
	private String endpoint;
	@Value("${api.chatgpt_api_key}")
	private String api_key;
	
	
	private String buildPrompt(String code,String to, String from) {
		return ("please translate the next code from" + from + "language to " + to + code);
	}
	public String translate(String code,String to, String from) {
		// Build input and API key params
	    JSONObject payload = new JSONObject();
	    JSONObject message = new JSONObject();
	    JSONArray messageList = new JSONArray();
	    
	    message.put("role", "user");
	    message.put("content",this.buildPrompt(code, to, from));
	    messageList.put(message);
	    
	    payload.put("model", "gpt-3.5-turbo"); // model is important
	    payload.put("messages", messageList);
	    payload.put("temperature", 0.7);
	    
	    StringEntity inputEntity = new StringEntity(payload.toString(), ContentType.APPLICATION_JSON);

	    // Build POST request
	    HttpPost post = new HttpPost(endpoint);
	    post.setEntity(inputEntity);
	    post.setHeader("Authorization", "Bearer " + api_key);
	    post.setHeader("Content-Type", "application/json");
		
	    // Send POST request and parse response
	    try (CloseableHttpClient httpClient = HttpClients.createDefault();
	      CloseableHttpResponse response = httpClient.execute(post)) {
	        HttpEntity resEntity = response.getEntity();
	        System.out.println("response" +resEntity);
	        String resJsonString = new String(resEntity.getContent().readAllBytes(), StandardCharsets.UTF_8);
	        JSONObject resJson = new JSONObject(resJsonString);
	        System.out.println("response" +resJson);
	        if (resJson.has("error")) {
	          String errorMsg = resJson.getString("error");
	          //("Chatbot API error: {}", errorMsg);
	          return "Error: " + errorMsg;
	        }

	        // Parse JSON response
	        JSONArray responseArray = resJson.getJSONArray("choices");
	        List<String> responseList = new ArrayList<>();

	        for (int i = 0; i < responseArray.length(); i++) {
	          JSONObject responseObj = responseArray.getJSONObject(i);
	          String responseString = responseObj.getJSONObject("message").getString("content");
	          responseList.add(responseString);
	        }

	        // Convert response list to JSON and return it
	        Gson gson = new Gson();
	        String jsonResponse = gson.toJson(responseList);
	        return jsonResponse;
	      } catch (IOException | JSONException e) {
	        //LOGGER.error("Error sending request: {}", e.getMessage());
	        return "Error sending request: " + e.getMessage();
	      }
	    
	}
	
	ChatBot(){
	}
}
