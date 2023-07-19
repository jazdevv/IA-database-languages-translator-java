package databasetranslateIA.dbtabasetranslateIA.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import databasetranslateIA.dbtabasetranslateIA.components.ChatBot;
import databasetranslateIA.dbtabasetranslateIA.components.InputLanguage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/translator")
public class controllerTranslator {
	@Autowired
	private ChatBot chatBot;
	
	@PostMapping("/translate")
	public String translateCode(@RequestBody InputLanguage inputLanguage) {
		System.out.println("req");
		String code = inputLanguage.getCode();
		String to = inputLanguage.getLanguage_to();
		String from = inputLanguage.getLanguage_from();
        String translationResult = chatBot.translate(code,to,from);
        System.out.println(translationResult);
        return translationResult;
	}
	
}
