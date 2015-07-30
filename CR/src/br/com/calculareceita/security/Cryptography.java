package br.com.calculareceita.security;

import java.security.NoSuchAlgorithmException;

public interface Cryptography {

  String encrypt(String value) throws NoSuchAlgorithmException;

}
