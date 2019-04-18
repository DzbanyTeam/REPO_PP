package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.polsl.pp.model.Role;
import pl.polsl.pp.repository.RoleRepository;
import pl.polsl.pp.service.interfaces.IRoleService;

import javax.annotation.PostConstruct;

@Service
public class RoleService implements IRoleService {

    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;

    public boolean saveRole(Role role) {
        try {
            roleRepository.save(role);
            return true;
        }catch(Exception ex) {
            return false;
        }
    }

    @Override
    public Role getByRole(String role) {
        Iterable<Role> roles = roleRepository.findAll();
        Role returnRole = null;

        for (Role rl : roles) {
            if (rl.getRole().equals(role)) {
                returnRole = rl;
            }
        }
        return returnRole;
    }

    @PostConstruct
    private void initializeRoleTable(){
        this.saveRole(new Role("ROLE_ADMIN"));
        this.saveRole(new Role("ROLE_CUSTOMER"));
    }
}
