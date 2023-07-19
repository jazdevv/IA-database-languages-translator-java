package databasetranslateIA.dbtabasetranslateIA.components;
import org.springframework.beans.factory.annotation.Autowired;
//import databasetranslateIA.dbtabasetranslateIA.components.ChatBot;

public class InputLanguage {
	public String language_from;
	public String language_to;
	public String code;
	
	
    public String getLanguage_from() {
		return language_from;
	}


	public String getLanguage_to() {
		return language_to;
	}


	public String getCode() {
		return code;
	}


	public InputLanguage(String language_from, String language_to, String code) {
        this.language_from = language_from;
        this.language_to = language_to;
        this.code = code;
    }
  
}
