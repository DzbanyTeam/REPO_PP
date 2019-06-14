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
        def acc = TestDataGenerator.createAdminAccount()
        adminAccountService.saveAdminAccount(acc)

        when:
        def adminAccount = adminAccountService.getAdminAccountById(acc.getId())

        then:
        adminAccount != null

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
        def returnedAdminAccount = adminAccountService.getAdminAccountByUsername(adminAccount.getUsername())

        then:
        returnedAdminAccount != null
    }
    def "should not return AdminAccount by username"() {
        given:
        String name = ""

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
        def success = adminAccountService.saveAdminAccount(adminAccount)

        then:
        success
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

    //getAllAdminAccounts
    def "should return all Admin Accounts"() {
        given:
        def oldNumberOfAccounts = adminAccountService.getAllAdminAccounts().size()

        when:
        adminAccountService.saveAdminAccount(TestDataGenerator.createAdminAccount())
        adminAccountService.saveAdminAccount(TestDataGenerator.createAdminAccount())

        then:
        adminAccountService.getAllAdminAccounts().size() == oldNumberOfAccounts + 2
        noExceptionThrown()
    }
}
