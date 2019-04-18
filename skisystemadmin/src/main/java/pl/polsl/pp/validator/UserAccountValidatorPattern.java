package pl.polsl.pp.validator;

class UserAccountValidatorPattern {


    private String usernamePattern = "[a-zA-Z0-9]{3,}";
    private String usernameErrorcodePattern = "USERNAME_ERROR_PATTERN";
    private String usernameMessagePattern = "Użyj małych lub wielkich liter lub cyfr oraz minimum 3 znaków";

    private String usernameErrorcodeRepetition = "USERNAME_ERROR_REPETITON";
    private String usernameMessageRepetition = "Taki login już istnieje";

    private String passwordPattern = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z]).{6,})";
    private String passwordErrorcode = "PASSWORD_ERROR";
    private String passwordMessage = "Użyj małych i wielkich liter, cyfr oraz minimum 6 znaków";

    private String namePattern = "[a-zA-Z]{3,}";
    private String nameErrorcode = "NAME_ERROR";
    private String nameMessage = "Użyj małych lub wielkich liter i minimum 3 znaków";

    private String surnamePattern = "[a-zA-Z]{2,}";
    private String surnameErrorcode = "SURNAME_ERROR";
    private String surnameMessage = "Użyj małych lub wielkich liter i minimum 2 znaków";

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
