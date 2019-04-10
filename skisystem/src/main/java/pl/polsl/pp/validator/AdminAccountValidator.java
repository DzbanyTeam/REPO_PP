package pl.polsl.pp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.polsl.pp.model.AdminAccount;
import pl.polsl.pp.service.interfaces.IAdminAccountService;

@Component
public class AdminAccountValidator implements Validator {


    @Autowired
    @Qualifier("adminAccountService")
    private IAdminAccountService adminAccountService;

    private AdminAccountValidatorPattern validatorPattern;

    public AdminAccountValidator() {
        validatorPattern = new AdminAccountValidatorPattern();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AdminAccount.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        AdminAccount adminAccount = (AdminAccount) o;

        // adding new account:
        if(adminAccount.getId().toString().equals("0")){
            validateUsernameForPattern(adminAccount.getUsername(), errors);
            validateUsernameForRepetition(adminAccount.getUsername(), errors);
            validatePassword(adminAccount.getPassword(), errors);
            validateName(adminAccount.getName(), errors);
            validateSurname(adminAccount.getSurname(),errors);
            validateEmail(adminAccount.getEmail(), errors);
            validatePhoneNumber(adminAccount.getPhoneNumber(), errors);
         // editing:
        } else {
            // changed password:
            if(!adminAccount.getPassword().isEmpty()) {
                validatePassword(adminAccount.getPassword(), errors);
            }
            // changed username:
            if(!adminAccountService.getAdminAccountById(adminAccount.getId()).getUsername().equals(adminAccount.getUsername())) {
                validateUsernameForRepetition(adminAccount.getUsername(), errors);
            }
            validateUsernameForPattern(adminAccount.getUsername(), errors);
            validateName(adminAccount.getName(), errors);
            validateSurname(adminAccount.getSurname(),errors);
            validateEmail(adminAccount.getEmail(), errors);
            validatePhoneNumber(adminAccount.getPhoneNumber(), errors);
        }

    }

    private void validateUsernameForPattern(String username, Errors errors) {
        if(!username.matches(validatorPattern.getUsernamePattern())) {
            errors.rejectValue("username", validatorPattern.getUsernameErrorcodePattern(), validatorPattern.getUsernameMessagePattern());
        }
    }

    private void validateUsernameForRepetition(String username, Errors errors) {
         if(adminAccountService.getAdminAccountByUsername(username) != null) {
            errors.rejectValue("username", validatorPattern.getUsernameErrorcodeRepetition(), validatorPattern.getUsernameMessageRepetition());
        }
    }

    private void validatePassword(String password, Errors errors){
        if(!password.matches(validatorPattern.getPasswordPattern())) {
            errors.rejectValue("password", validatorPattern.getPasswordErrorcode(), validatorPattern.getPasswordMessage());
        }
    }

    private void validateName(String name, Errors errors){
        if(!name.matches(validatorPattern.getNamePattern())) {
            errors.rejectValue("name", validatorPattern.getNameErrorcode(), validatorPattern.getNameMessage());
        }
    }

    private void validateSurname(String surname, Errors errors){
        if(!surname.matches(validatorPattern.getSurnamePattern())) {
            errors.rejectValue("surname", validatorPattern.getSurnameErrorcode(), validatorPattern.getSurnameMessage());
        }
    }


    private void validateEmail(String email, Errors errors){
        if(!email.matches(validatorPattern.getEmailPattern())) {
            errors.rejectValue("email", validatorPattern.getEmailErrorcode(), validatorPattern.getEmailMessage());
        }
    }

    private void validatePhoneNumber(String phoneNumber, Errors errors){
        if(!phoneNumber.matches(validatorPattern.getPhoneNumberPattern())) {
            errors.rejectValue("phoneNumber", validatorPattern.getPhoneNumberErrorcode(), validatorPattern.getPhoneNumberMessage());
        }
    }

}
