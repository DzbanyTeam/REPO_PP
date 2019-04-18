package pl.polsl.pp.validator;

import org.springframework.validation.Errors;

public class UserAccountValidator {

    protected UserAccountValidatorPattern validatorPattern;

    UserAccountValidator() {
        validatorPattern = new UserAccountValidatorPattern();
    }

    protected void validateUsernameForPattern(String username, Errors errors) {
        if(!username.matches(validatorPattern.getUsernamePattern())) {
            errors.rejectValue("username", validatorPattern.getUsernameErrorcodePattern(), validatorPattern.getUsernameMessagePattern());
        }
    }

    protected void validatePassword(String password, Errors errors){
        if(!password.matches(validatorPattern.getPasswordPattern())) {
            errors.rejectValue("password", validatorPattern.getPasswordErrorcode(), validatorPattern.getPasswordMessage());
        }
    }

    protected void validateName(String name, Errors errors){
        if(!name.matches(validatorPattern.getNamePattern())) {
            errors.rejectValue("name", validatorPattern.getNameErrorcode(), validatorPattern.getNameMessage());
        }
    }

    protected void validateSurname(String surname, Errors errors){
        if(!surname.matches(validatorPattern.getSurnamePattern())) {
            errors.rejectValue("surname", validatorPattern.getSurnameErrorcode(), validatorPattern.getSurnameMessage());
        }
    }


    protected void validateEmail(String email, Errors errors){
        if(!email.matches(validatorPattern.getEmailPattern())) {
            errors.rejectValue("email", validatorPattern.getEmailErrorcode(), validatorPattern.getEmailMessage());
        }
    }

    protected void validatePhoneNumber(String phoneNumber, Errors errors){
        if(!phoneNumber.matches(validatorPattern.getPhoneNumberPattern())) {
            errors.rejectValue("phoneNumber", validatorPattern.getPhoneNumberErrorcode(), validatorPattern.getPhoneNumberMessage());
        }
    }

}
