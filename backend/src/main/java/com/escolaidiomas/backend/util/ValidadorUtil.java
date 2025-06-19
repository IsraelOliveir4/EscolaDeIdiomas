package com.escolaidiomas.backend.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorUtil {

    public static boolean validarCpf(String cpf) {
        // Expressão regular para CPF (aceita tanto com ou sem pontos e traços)
        String regex = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }

    // Validação de E-mail
    public static boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Validação de Telefone (Formato simples, exemplo: (11) 12345-6789)
    public static boolean validarTelefone(String telefone) {
        String regex = "^\\(\\d{2}\\) \\d{5}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefone);
        return matcher.matches();
    }
}
