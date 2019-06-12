package pl.polsl.pp.skisystemdomain

import groovy.transform.CompileStatic
import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Person
import pl.polsl.pp.model.AdminAccount
import pl.polsl.pp.model.CustomerAccount
import pl.polsl.pp.model.DayOfTheWeek
import pl.polsl.pp.model.Lift
import pl.polsl.pp.model.LiftBusinessHours

import java.sql.Time

@CompileStatic
class TestDataGenerator {

    //AdminAccount Generator
    static AdminAccount createAdminAccount(){
        Fairy f = Fairy.create()
        Person p = f.person()

        new AdminAccount(p.username(),p.password(),true, p.firstName(), p.lastName(),p.email(),p.telephoneNumber())

    }

    static AdminAccount createAdminAccountWithoutPassword(){
        Fairy f = Fairy.create()
        Person p = f.person()

        new AdminAccount(p.username(),null,true, p.firstName(), p.lastName(),p.email(),p.telephoneNumber())

    }
    static AdminAccount createAdminAccountWithoutUsername(){
        Fairy f = Fairy.create()
        Person p = f.person()

        new AdminAccount(null,p.password(),true, p.firstName(), p.lastName(),p.email(),p.telephoneNumber())

    }

    //CustomerAccount Generator
    static CustomerAccount createCustomerAccount() {
        Fairy f = Fairy.create()
        Person p = f.person()

        new CustomerAccount(p.username(), p.password(), true, p.firstName(), p.lastName(), p.email(), p.telephoneNumber())
    }
    static CustomerAccount createCustomerAccountWithoutPassword(){
        Fairy f = Fairy.create()
        Person p = f.person()

        new CustomerAccount(p.username(),null,true, p.firstName(), p.lastName(),p.email(),p.telephoneNumber())

    }
    static CustomerAccount createCustomerAccountWithoutUsername(){
        Fairy f = Fairy.create()
        Person p = f.person()

        new CustomerAccount(null,p.password(),true, p.firstName(), p.lastName(),p.email(),p.telephoneNumber())

    }

    //LiftBusinessHours Generator
    static LiftBusinessHours createLiftBusinessHour(int timeOffset) {
        new LiftBusinessHours(null, new Time(10 + timeOffset,30,00), new Time(13 + timeOffset,30,00), null)
    }

    //Lift Generator
    static Lift createLift(int offset) {
        new Lift("New", 400 + offset, 1000 + offset, 1600 + offset, true)
    }

}
