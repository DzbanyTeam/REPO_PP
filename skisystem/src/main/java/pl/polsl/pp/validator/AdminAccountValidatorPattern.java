package pl.polsl.pp.validator;

class AdminAccountValidatorPattern {


    private String usernamePattern = "[a-zA-Z0-9_-]{3,}";
    private String usernameErrorcodePattern = "USERNAME_ERROR_PATTERN";
    private String usernameMessagePattern = "Login może zawierać małe i wielkie litery, cyfry lub znaki: -_ oraz" +
            "powinien składać się z minimum trzech znaków";

    private String usernameErrorcodeRepetition = "USERNAME_ERROR_REPETITON";
    private String usernameMessageRepetition = "Taki login juz istnieje";


    private String passwordPattern = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%!-_]).{8,})";
    private String passwordErrorcode = "PASSWORD_ERROR";
    private String passwordMessage = "Hasło powinno zawierać małe i wielkie litery, cyfrę oraz jeden ze znaków @#$%!-_ oraz" +
            "powinno składać się z minimum 8 znaków";

    private String namePattern = "[a-zA-Z]{3,}";
    private String nameErrorcode = "NAME_ERROR";
    private String nameMessage = "Imię może składać się tylko z liter i minimum 3 znaków";

    private String surnamePattern = "[a-zA-Z]{2,}";
    private String surnameErrorcode = "SURNAME_ERROR";
    private String surnameMessage = "Nazwisko może składać się tylko z liter i minimum 2 znaków";

    private String emailPattern = "^[a-zA-Z0-9_!#$%&+/=?-]+@[a-zA-Z0-9.-]+$";
    private String emailErrorcode = "EMIAL_ERROR";
    private String emailMessage = "Nieprawidłowy format adresu e-mail";

    private String phoneNumberPattern = "[0-9+]{7,}";
    private String phoneNumberErrorcode = "PHONENUMBER_ERROR";
    private String phoneNumberMessage = "Nieprawidłowy numer telefonu";


    public String getUsernamePattern() {
        return usernamePattern;
    }

    public String getUsernameErrorcodePattern() {
        return usernameErrorcodePattern;
    }

    public String getUsernameMessagePattern() {
        return usernameMessagePattern;
    }

    public String getUsernameErrorcodeRepetition() {
        return usernameErrorcodeRepetition;
    }

    public String getUsernameMessageRepetition() {
        return usernameMessageRepetition;
    }

    public String getPasswordPattern() {
        return passwordPattern;
    }

    public String getPasswordErrorcode() {
        return passwordErrorcode;
    }

    public String getPasswordMessage() {
        return passwordMessage;
    }

    public String getNamePattern() {
        return namePattern;
    }

    public String getNameErrorcode() {
        return nameErrorcode;
    }

    public String getNameMessage() {
        return nameMessage;
    }

    public String getSurnamePattern() {
        return surnamePattern;
    }

    public String getSurnameErrorcode() {
        return surnameErrorcode;
    }

    public String getSurnameMessage() {
        return surnameMessage;
    }

    public String getEmailPattern() {
        return emailPattern;
    }

    public String getEmailErrorcode() {
        return emailErrorcode;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public String getPhoneNumberPattern() {
        return phoneNumberPattern;
    }

    public String getPhoneNumberErrorcode() {
        return phoneNumberErrorcode;
    }

    public String getPhoneNumberMessage() {
        return phoneNumberMessage;
    }
}
