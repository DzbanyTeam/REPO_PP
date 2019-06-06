package pl.polsl.pp.skisystemdomain

import groovy.transform.CompileStatic
import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Person
import pl.polsl.pp.model.AdminAccount

@CompileStatic
class TestDataGenerator {

    static AdminAccount createAdminAccount(){
        Fairy f = Fairy.create()
        Person p = f.person()

        new AdminAccount(p.username(),p.password(),true, p.firstName(), p.lastName(),p.email(),p.telephoneNumber())

    }

}
