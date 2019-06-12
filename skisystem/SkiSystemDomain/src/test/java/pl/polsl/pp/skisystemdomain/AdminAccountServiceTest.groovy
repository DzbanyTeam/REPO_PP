package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.AdminAccount
import pl.polsl.pp.service.interfaces.IAdminAccountService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class AdminAccountServiceTest extends Specification {

    @Autowired
    private IAdminAccountService adminAccountService

    //getAdminAccountById
    def "should return AdminAccount by id"() {
        given:
        adminAccountService.saveAdminAccount(TestDataGenerator.createAdminAccount())
        Long id = 1
        def adminAccount = adminAccountService.getAdminAccountById(id)

        expect:
        adminAccount.getId() == id
    }
    def "should not return AdminAccount by id"() {
        when:
        def adminAccount = adminAccountService.getAdminAccountById(0)

        then:
        noExceptionThrown()
    }
    //getAdminAccountByUsername
    def "should return AdminAccount by username"() {
        given:
        def adminAccount = TestDataGenerator.createAdminAccount()
        def wasSuccessful = adminAccountService.saveAdminAccount(adminAccount)

        when:
        AdminAccount returnedAdminAccount = adminAccountService.getAdminAccountByUsername(adminAccount.getName())

        then:
        adminAccount.name == returnedAdminAccount.getName()
        noExceptionThrown()

    }
    def "should not return AdminAccount by username"() {
        given:
        String name = "totallyRandomName"

        when:
        def returnedAdminAccount = adminAccountService.getAdminAccountByUsername(name)

        then:
        returnedAdminAccount == null
        noExceptionThrown()
    }

    //saveAdminAccount
    def "should add new Admin"() {
        given:
        def adminAccount = TestDataGenerator.createAdminAccount()

        when:
        def wasSuccessful = adminAccountService.saveAdminAccount(adminAccount)

        then:
        wasSuccessful
        noExceptionThrown()
    }
    def "shouldn not add new Admin"() {
        given:
        def adminAccount = TestDataGenerator.createIncorrectAdminAccount()

        when:
        def wasSuccessful = adminAccountService.saveAdminAccount(adminAccount)

        then:
        !wasSuccessful
        noExceptionThrown()
    }


}
