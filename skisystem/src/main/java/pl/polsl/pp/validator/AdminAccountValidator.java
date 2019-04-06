package pl.polsl.pp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.polsl.pp.model.AdminAccount;
import pl.polsl.pp.service.AdminAccountServiceInterface;

@Component
public class AdminAccountValidator implements Validator {


    @Autowired
    @Qualifier("adminAccountService")
    private AdminAccountServiceInterface adminAccountService;

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

        if(adminAccount.getId().toString().equals("0")){
            validateUsername(adminAccount.getUsername(), errors);
            validatePassword(adminAccount.getPassword(), errors);
            validateName(adminAccount.getName(), errors);
            validateSurname(adminAccount.getSurname(),errors);
            validateEmail(adminAccount.getEmail(), errors);
            validatePhoneNumber(adminAccount.getPhoneNumber(), errors);
        } else{
            validateUsername(adminAccount.getUsername(), errors);
            validateName(adminAccount.getName(), errors);
            validateSurname(adminAccount.getSurname(),errors);
            validateEmail(adminAccount.getEmail(), errors);
            validatePhoneNumber(adminAccount.getPhoneNumber(), errors);
        }

    }

    private void validateUsername(String username, Errors errors) {
        if(!username.matches(validatorPattern.getUsernamePattern())) {
            errors.rejectValue("username", validatorPattern.getUsernameErrorcodePattern(), validatorPattern.getUsernameMessagePattern());
        } else if(adminAccountService.getAdminAccountByUsername(username) != null) {
            errors.rejectValue("username", validatorPattern.getUsernameErrorcodeRepetition(), validatorPattern.getUsernameMessageRepetition());
        }
    }

    private void validatePassword(String password, Errors errors){
        if(!password.matches(validatorPattern.getPasswordPattern())) {
            errors.rejectValue("password", validatorPattern.getPasswordErrorcode(), validatorPattern.getPasswordMessage());
        }
    }

    private void validateSurname(String surname, Errors errors){
        if(!surname.matches(validatorPattern.getPasswordPattern())) {
            errors.rejectValue("surname", validatorPattern.getSurnameErrorcode(), validatorPattern.getSurnameMessage());
        }
    }

    private void validateName(String name, Errors errors){
        if(!name.matches(validatorPattern.getPasswordPattern())) {
            errors.rejectValue("name", validatorPattern.getNameErrorcode(), validatorPattern.getNameMessage());
        }
    }

    private void validateEmail(String email, Errors errors){
        if(!email.matches(validatorPattern.getPasswordPattern())) {
            errors.rejectValue("email", validatorPattern.getEmailErrorcode(), validatorPattern.getEmailMessage());
        }
    }

    private void validatePhoneNumber(String phoneNumber, Errors errors){
        if(!phoneNumber.matches(validatorPattern.getPasswordPattern())) {
            errors.rejectValue("phoneNumber", validatorPattern.getPhoneNumberErrorcode(), validatorPattern.getPhoneNumberMessage());
        }
    }

}
