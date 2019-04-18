package pl.polsl.pp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.polsl.pp.model.CustomerAccount;
import pl.polsl.pp.service.interfaces.ICustomerAccountService;

@Component
public class CustomerAccountValidator extends UserAccountValidator implements Validator {

    @Autowired
    @Qualifier("customerAccountService")
    private ICustomerAccountService customerAccountService;

    CustomerAccountValidator() {
        super();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // odkomentowac gdy walidator bedzie potrzebny
        return CustomerAccount.class.isAssignableFrom(aClass);
        //return false;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void validate(Object o, Errors errors) {

        CustomerAccount customerAccount = (CustomerAccount) o;

        if(customerAccount.getId().toString().equals("0")){
            validateUsernameForPattern(customerAccount.getUsername(), errors);
            validateUsernameForRepetition(customerAccount.getUsername(), errors);
            validatePassword(customerAccount.getPassword(), errors);
            validateName(customerAccount.getName(), errors);
            validateSurname(customerAccount.getSurname(),errors);
            validateEmail(customerAccount.getEmail(), errors);
            validatePhoneNumber(customerAccount.getPhoneNumber(), errors);
            // editing:
        } else {
            // changed password:
            if(!customerAccount.getPassword().isEmpty()) {
                validatePassword(customerAccount.getPassword(), errors);
            }
            // changed username:
            if(!customerAccountService.getCustomerAccountById(customerAccount.getId()).getUsername().equals(customerAccount.getUsername())) {
                validateUsernameForRepetition(customerAccount.getUsername(), errors);
            }
            validateUsernameForPattern(customerAccount.getUsername(), errors);
            validateName(customerAccount.getName(), errors);
            validateSurname(customerAccount.getSurname(),errors);
            validateEmail(customerAccount.getEmail(), errors);
            validatePhoneNumber(customerAccount.getPhoneNumber(), errors);
        }

    }

    private void validateUsernameForRepetition(String username, Errors errors) {
        if(customerAccountService.getCustomerAccountByUsername(username) != null) {
            errors.rejectValue("username", validatorPattern.getUsernameErrorcodeRepetition(), validatorPattern.getUsernameMessageRepetition());
        }
    }
}
