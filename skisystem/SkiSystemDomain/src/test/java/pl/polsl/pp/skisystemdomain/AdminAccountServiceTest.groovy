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
        adminAccountService.getAdminAccountById(0)

        then:
        thrown NoSuchElementException
    }
    //getAdminAccountByUsername
    def "should return AdminAccount by username"() {
        given:
        def adminAccount = TestDataGenerator.createAdminAccount()
        adminAccountService.saveAdminAccount(adminAccount)

        when:
        AdminAccount returnedAdminAccount = adminAccountService.getAdminAccountByUsername(adminAccount.getUsername())

        then:
        adminAccount.getName() == returnedAdminAccount.getName()
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
    def "should change Admin Account's Password"() {
        given:
        def adminAccount = TestDataGenerator.createAdminAccount()

        when:
        adminAccountService.saveAdminAccount(adminAccount)
        adminAccount.setPassword("NewPassword")
        adminAccountService.saveAdminAccount(adminAccount)

        then:
        adminAccount.getPassword() == adminAccountService.getAdminAccountById(adminAccount.getId()).getPassword()
        noExceptionThrown()
    }
    def "should not add new Admin"() {
        expect:
        !adminAccountService.saveAdminAccount(TestDataGenerator.createAdminAccountWithoutPassword())
        !adminAccountService.saveAdminAccount(TestDataGenerator.createAdminAccountWithoutUsername())
    }
    //deleteAdminAccounts
    def "should delete Admin Accounts"() {
        given:
        def adminAccount = TestDataGenerator.createAdminAccount()
        def adminAccount1 = TestDataGenerator.createAdminAccount()

        adminAccountService.saveAdminAccount(adminAccount)
        adminAccountService.saveAdminAccount(adminAccount1)

        List<Long> accountsList = [adminAccount.getId(), adminAccount1.getId()]

        when:
        adminAccountService.deleteAdminAccounts(accountsList)

        then:
        adminAccountService.getAdminAccountByUsername(adminAccount.getUsername()) == null
        adminAccountService.getAdminAccountByUsername(adminAccount1.getUsername()) == null
        noExceptionThrown()
    }
    def "should not delete Admin Accounts"() {
        expect:
        adminAccountService.deleteAdminAccounts([-1, 0]) == false
    }

    //deactivateAdminAccounts
    def "should deactivate Admin Accounts"() {
        given:
        def adminAccount = TestDataGenerator.createAdminAccount()
        def adminAccount1 = TestDataGenerator.createAdminAccount()

        adminAccountService.saveAdminAccount(adminAccount)
        adminAccountService.saveAdminAccount(adminAccount1)

        when:
        adminAccountService.deactivateAdminAccounts([adminAccount.getId(), adminAccount1.getId()])

        then:
        !adminAccount.getIsActive()
        !adminAccount1.getIsActive()
    }
    def "should not deactivate Admin Accounts"() {
        expect:
        !adminAccountService.deactivateAdminAccounts([-1, 0])
    }

    //activateAdminAccounts
    def "should activate Admin Accounts"() {
        given:
        def adminAccount = TestDataGenerator.createAdminAccount()
        adminAccount.setIsActive(false)
        def adminAccount1 = TestDataGenerator.createAdminAccount()
        adminAccount1.setIsActive(false)

        adminAccountService.saveAdminAccount(adminAccount)
        adminAccountService.saveAdminAccount(adminAccount1)

        when:
        adminAccountService.activateAdminAccounts([adminAccount.getId(), adminAccount1.getId()])

        then:
        adminAccount.getIsActive()
        adminAccount1.getIsActive()
        noExceptionThrown()
    }
    def "should not activate Admin Accounts"() {
        expect:
        !adminAccountService.activateAdminAccounts([-1, 0])
    }

    //getAllAdminAccounts
    def "should return all Admin Accounts"() {
        given:
        def accountsList = adminAccountService.getAllAdminAccounts()
        def oldNumberOfAccounts = accountsList.size()
        adminAccountService.saveAdminAccount(TestDataGenerator.createAdminAccount())
        adminAccountService.saveAdminAccount(TestDataGenerator.createAdminAccount())

        when:
        accountsList = adminAccountService.getAllAdminAccounts()

        then:
        oldNumberOfAccounts + 2 == accountsList.size()
        noExceptionThrown()
    }
}
