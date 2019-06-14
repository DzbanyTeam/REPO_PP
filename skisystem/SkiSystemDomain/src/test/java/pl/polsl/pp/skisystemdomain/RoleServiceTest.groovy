package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.Role
import pl.polsl.pp.service.interfaces.IRoleService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class RoleServiceTest extends Specification {

    @Autowired
    private IRoleService roleService

    //saveRole
    def "should add new Role"() {
        given:
        def role = new Role("TEST_ROLE")

        when:
        def success = roleService.saveRole(role)

        then:
        success
        noExceptionThrown()
    }
    def "should not add new Role"() {
        given:
        def role = new Role(null)

        when:
        def success = roleService.saveRole(role)

        then:
        !success
        noExceptionThrown()
    }

    //getByRole
    def "should return Role"() {
        given:
        def role = new Role("TEST_ROLE")
        roleService.saveRole(role)

        when:
        def returnedRole = roleService.getByRole("TEST_ROLE")

        then:
        returnedRole != null
        noExceptionThrown()
    }
}
