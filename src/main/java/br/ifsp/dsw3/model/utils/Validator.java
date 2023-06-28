package br.ifsp.dsw3.model.utils;

public class Validator {
    public static boolean phone(String phone) {
        System.out.println(phone);
        if (phone == null) {
            return false;
        }
        System.out.println("phone");
        System.out.println(phone.matches("\\(\\d{2}\\)\\s\\d{4,5}\\-\\d{4}"));
        return phone.matches("\\(\\d{2}\\)\\s\\d{4,5}\\-\\d{4}");
    }

    public static boolean text(String text) {
        System.out.println(text);
        if (text == null) {
            return false;
        }
        System.out.println("text");
        System.out.println(text.matches("[A-Z][a-z]{3,}"));
        return text.matches("[A-Z][a-z]{3,}");
    }

    public static boolean cpf(String CPF) {
        if (CPF == null) {
            return false;
        }
        System.out.println("crm");
        System.out.println(CPF.matches("^(([0-9]{3}).([0-9]{3}).([0-9]{3})-([0-9]{2}))*$"));
        return CPF.matches("^(([0-9]{3}).([0-9]{3}).([0-9]{3})-([0-9]{2}))*$");
    }

    public static boolean crm(String CRM) {
        if (CRM == null) {
            return false;
        }
        System.out.println("cpf");
        System.out.println(CRM.matches("(([A-Za-z]{2})([0-9]{6}))"));
        return CRM.matches("(([A-Za-z]{2})([0-9]{6}))");
    }
}
