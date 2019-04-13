package pl.polsl.pp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.polsl.pp.model.AdminAccount;
import pl.polsl.pp.service.interfaces.IAdminAccountService;

@Component
public class AdminAccountValidator extends UserAccountValidator implements Validator {


    @Autowired
    @Qualifier("adminAccountService")
    private IAdminAccountService adminAccountService;

    public AdminAccountValidator() {
       super();
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

    private void validateUsernameForRepetition(String username, Errors errors) {
        if(adminAccountService.getAdminAccountByUsername(username) != null) {
            errors.rejectValue("username", validatorPattern.getUsernameErrorcodeRepetition(), validatorPattern.getUsernameMessageRepetition());
        }
    }


}
