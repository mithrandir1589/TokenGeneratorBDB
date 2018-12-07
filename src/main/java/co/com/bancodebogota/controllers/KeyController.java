package co.com.bancodebogota.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.com.bancodebogota.services.TokenGenerator;

@RestController
public class KeyController {
	@Autowired
	private TokenGenerator tokenGenerator;
	
	@GetMapping("/getToken/{username}")
	public String getToken(@PathVariable("username") String username) {
		return tokenGenerator.getToken(username);
	}
}
