package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.CustomerAccount
import pl.polsl.pp.service.interfaces.ICustomerAccountService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class CustomerAccountServiceTest extends Specification {

    @Autowired
    private ICustomerAccountService customerAccountService

    //getCustomerAccountById
    def "should return CustomerAccount by id"() {
        given:
        customerAccountService.saveCustomerAccount(TestDataGenerator.createCustomerAccount())
        Long id = 1
        when:
        def customerAccount = customerAccountService.getCustomerAccountById(id)

        then:
        customerAccount.getId() == id
        noExceptionThrown()
    }
    def "should not return CustomerAccount by id"() {
        when:
        customerAccountService.getCustomerAccountById(0)

        then:
        thrown NoSuchElementException
    }
    //getCustomerAccountByUsername
    def "should return CustomerAccount by username"() {
        given:
        def customerAccount = TestDataGenerator.createCustomerAccount()
        customerAccountService.saveCustomerAccount(customerAccount)

        when:
        CustomerAccount returnedCustomerAccount = customerAccountService.getCustomerAccountByUsername(customerAccount.getUsername())

        then:
        customerAccount.getName() == returnedCustomerAccount.getName()
        noExceptionThrown()

    }
    def "should not return CustomerAccount by username"() {
        given:
        String name = "totallyRandomName"

        when:
        def returnedCustomerAccount = customerAccountService.getCustomerAccountByUsername(name)

        then:
        returnedCustomerAccount == null
        noExceptionThrown()
    }

    //saveCustomerAccount
    def "should add new Customer"() {
        given:
        def customerAccount = TestDataGenerator.createCustomerAccount()

        when:
        def wasSuccessful = customerAccountService.saveCustomerAccount(customerAccount)

        then:
        wasSuccessful
        noExceptionThrown()
    }
    def "should change Customer Account's Password"() {
        given:
        def customerAccount = TestDataGenerator.createCustomerAccount()

        when:
        customerAccountService.saveCustomerAccount(customerAccount)
        customerAccount.setPassword("NewPassword")
        customerAccountService.saveCustomerAccount(customerAccount)

        then:
        customerAccount.getPassword() == customerAccountService.getCustomerAccountById(customerAccount.getId()).getPassword()
        noExceptionThrown()
    }
    def "should not add new Customer"() {
        expect:
        !customerAccountService.saveCustomerAccount(TestDataGenerator.createCustomerAccountWithoutPassword())
        !customerAccountService.saveCustomerAccount(TestDataGenerator.createCustomerAccountWithoutUsername())
    }
    //deleteCustomerAccounts
    def "should delete Customer Accounts"() {
        given:
        def customerAccount = TestDataGenerator.createCustomerAccount()
        def customerAccount1 = TestDataGenerator.createCustomerAccount()

        customerAccountService.saveCustomerAccount(customerAccount)
        customerAccountService.saveCustomerAccount(customerAccount1)

        List<Long> accountsList = [customerAccount.getId(), customerAccount1.getId()]

        when:
        customerAccountService.deleteCustomerAccounts(accountsList)

        then:
        customerAccountService.getCustomerAccountByUsername(customerAccount.getUsername()) == null
        customerAccountService.getCustomerAccountByUsername(customerAccount1.getUsername()) == null
        noExceptionThrown()
    }
    def "should not delete Customer Accounts"() {
        expect:
        customerAccountService.deleteCustomerAccounts([-1, 0]) == false
    }

    //deactivateCustomerAccounts
    def "should deactivate Customer Accounts"() {
        given:
        def customerAccount = TestDataGenerator.createCustomerAccount()
        def customerAccount1 = TestDataGenerator.createCustomerAccount()

        customerAccountService.saveCustomerAccount(customerAccount)
        customerAccountService.saveCustomerAccount(customerAccount1)

        when:
        customerAccountService.deactivateCustomerAccounts([customerAccount.getId(), customerAccount1.getId()])

        then:
        !customerAccount.getIsActive()
        !customerAccount1.getIsActive()
    }
    def "should not deactivate Customer Accounts"() {
        expect:
        !customerAccountService.deactivateCustomerAccounts([-1, 0])
    }

    //activateCustomerAccounts
    def "should activate Customer Accounts"() {
        given:
        def customerAccount = TestDataGenerator.createCustomerAccount()
        customerAccount.setIsActive(false)
        def customerAccount1 = TestDataGenerator.createCustomerAccount()
        customerAccount1.setIsActive(false)

        customerAccountService.saveCustomerAccount(customerAccount)
        customerAccountService.saveCustomerAccount(customerAccount1)

        when:
        customerAccountService.activateCustomerAccounts([customerAccount.getId(), customerAccount1.getId()])

        then:
        customerAccount.getIsActive()
        customerAccount1.getIsActive()
        noExceptionThrown()
    }
    def "should not activate Customer Accounts"() {
        expect:
        !customerAccountService.activateCustomerAccounts([-1, 0])
    }

    //getAllCustomerAccounts
    def "should return all Customer Accounts"() {
        given:
        def accountsList = customerAccountService.getAllCustomerAccounts()
        def oldNumberOfAccounts = accountsList.size()
        customerAccountService.saveCustomerAccount(TestDataGenerator.createCustomerAccount())
        customerAccountService.saveCustomerAccount(TestDataGenerator.createCustomerAccount())

        when:
        accountsList = customerAccountService.getAllCustomerAccounts()

        then:
        accountsList.size() == oldNumberOfAccounts + 2
        noExceptionThrown()
    }
}
