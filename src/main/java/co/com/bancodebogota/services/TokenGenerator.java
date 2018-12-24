/**
 * Clase encargada de la generacion y retorno del token dado un usuario.
 * @author mquint2
 */

package co.com.bancodebogota.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenGenerator implements ITokenGenerator{

	@Autowired
	private KeyGenerator keyGenerator;
	
	/**
	 * Retorna el token generado para que sea utilizado por el controlador KeyController
	 */
	@Override
	public String getToken(String username) {
		return generateToken(username);
	}
	
	/**
	 * Metodo que genera el token de acceso para usuarios autenticados, firmado con la llave privada
	 * @param username Usuario al cual se le va a crear el token JWT
	 * @return token que será retornado al usuario para que interactue con una aplicacion especifica. 
	 */
	private String generateToken(String username) {
		//TODO asignar duracion del token dependiendo de la aplicacion que solicite el recurso. Asi como otros "claims"
		//que pueda contener dicho token (información adicional)
		String token = null;
		try {
			token = Jwts.builder()
					.setSubject(username)
					.signWith(SignatureAlgorithm.RS512, keyGenerator.getPrivateKey())
					.compact();
		} catch (NoSuchAlgorithmException e) {
			// TODO Una vez implementada la politica de logs, imprimir descripcion de la exepcion, la cual ocurre cuando 
			//el algoritmo usado no esta disponible en el ambiente
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Una vez implementada la politica de logs, imprimir descripcion de la exepcion
			e.printStackTrace();
		}
		return token;
	}
	

}
