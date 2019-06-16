package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.Role
import pl.polsl.pp.repository.AdminAccountRepository
import pl.polsl.pp.service.interfaces.IAdminAccountService
import pl.polsl.pp.service.interfaces.IPermissionService
import pl.polsl.pp.service.interfaces.IRoleService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class PermissionServiceTest extends Specification {

    @Autowired
    private IPermissionService permissionService
    @Autowired
    private IAdminAccountService adminAccountService
    @Autowired
    private IRoleService roleService
    @Autowired
    private AdminAccountRepository repo

    //savePermissions
    def "should add Permission"() {
        given:
        def adminAccount = TestDataGenerator.createAdminAccount()
        def role = roleService.getByRole("ROLE_ADMIN")

        repo.save(adminAccount)

        when:
        def success = permissionService.savePermission(adminAccount.getId(), role.getId())

        then:
        success
        noExceptionThrown()
    }

    //deletePermissions
    def "should delete Permission"() {
        given:
        def adminAccount = TestDataGenerator.createAdminAccount()
        def role = new Role("TEST_ROLE")

        roleService.saveRole(role)
        repo.save(adminAccount)
        permissionService.savePermission(adminAccount.getId(), role.getId())

        when:
        def success = permissionService.deletePermissions([adminAccount.getId()])

        then:
        success
        noExceptionThrown()
    }
}
