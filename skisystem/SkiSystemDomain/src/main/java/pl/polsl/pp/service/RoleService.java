package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.polsl.pp.model.Role;
import pl.polsl.pp.repository.RoleRepository;
import pl.polsl.pp.service.interfaces.IRoleService;

import javax.annotation.PostConstruct;

/**
 * Usługa wykonująca operacje na obiektach typu Role
 */
@Service
public class RoleService implements IRoleService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli roles
     */
    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;

    /**
     * Dodaje dany obiekt Role do bazy danych
     * @param role obiekt Role do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    public boolean saveRole(Role role) {
        try {
            roleRepository.save(role);
            return true;
        }catch(Exception ex) {
            return false;
        }
    }

    /**
     * Zwraca obiekt Role z daną nazwą roli
     * @param role Role name
     * @return Role
     */
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

    /**
     * Dodaje role Admina i Klienta na wypadek pustej bazy danych
     */
    @PostConstruct
    private void initializeRoleTable(){
        this.saveRole(new Role("ROLE_ADMIN"));
        this.saveRole(new Role("ROLE_CUSTOMER"));
    }
}
