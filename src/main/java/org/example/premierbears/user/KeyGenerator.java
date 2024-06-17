package org.example.premierbears.user;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class KeyGenerator {

    public static void main(String[] args) {
        // Générer une clé secrète pour l'algorithme HMAC-SHA256
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Convertir la clé secrète en chaîne de caractères
        String secretString = Base64.getEncoder().encodeToString(key.getEncoded());

        // Afficher la clé secrète
        System.out.println(secretString);
    }
}