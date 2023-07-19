package databasetranslateIA.dbtabasetranslateIA.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChatBot {
	@Value("${api.endpoint}")
	private String endpoint;
	@Value("${api.chatgpt_api_key}")
	private String api_key;
	
	public String translate(String message,String to, String from) {
		return "translated message";
	}
	
	ChatBot(){
	}
}
