package databasetranslateIA.dbtabasetranslateIA.components;

public class InputLanguage {
	private String language_from;
	private String language_to;
	private String code;
	
	public InputLanguage() {
        // Default constructor
    }

    public InputLanguage(String language_from, String language_to, String code) {
        this.language_from = language_from;
        this.language_to = language_to;
        this.code = code;
    }
	
    //translate the from to the to language and return the translated code
    public String translate() {
    	return "trsnlated code" + this.code;
    }
}
