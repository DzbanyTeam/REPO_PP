package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.service.interfaces.IAdminAccountService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class AdminAccountServiceTest extends Specification {

    @Autowired
    private IAdminAccountService adminAccountService

    def "should add new Admin"() {
        given:
        def adminAccount = TestDataGenerator.createAdminAccount()

        when:
        def wasSuccesfull = adminAccountService.saveAdminAccount(adminAccount)

        then:
        wasSuccesfull
        noExceptionThrown()
    }


}
