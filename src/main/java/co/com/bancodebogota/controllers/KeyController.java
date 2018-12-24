/**
 * @author mquint2
 */
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
	
	/**
	 * Permite obtener el token generado para un usuario que fue autenticado con exito
	 * @param username Usuario que fue autenticado
	 * @return token de acceso
	 */
	@GetMapping("/getToken/{username}")
	public String getToken(@PathVariable("username") String username) {
		return tokenGenerator.getToken(username);
	}
}
