package br.com.livraria.loja.security;

import org.jboss.security.Base64Encoder;

public class PassGenerator {
    public static void main(String[] args) throws Exception {
        Base64Encoder.main(new String[]{"matt", "SHA-256"});
    }
}
