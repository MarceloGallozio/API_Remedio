package com.remedios.curso.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.remedios.curso.usuarios.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		try {
		    var algorithm = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("Remedios_api") // Sinaliza de quem é a API
		        .withSubject(usuario.getLogin()) // Pega o login para validacao
		       // .withClaim("id", usuario.getId())  Ao logar mostra o ID do usuario
		        .withExpiresAt(dataExpiracao()) // Coloca um tempo para o token expirar
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Erro ao gerar token", exception);
		}
	}

	public String getSubject(String TokenJWT) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
		    return JWT.require(algorithm)
		        .withIssuer("Remedios_api")
		        .build()
		        .verify(TokenJWT)
		        .getSubject();
		        
		} catch (JWTVerificationException exception){
			throw new RuntimeException("Token inválido ou expirado");
		}
	}
	
	private Instant dataExpiracao() {
		return LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"));
	}
}
