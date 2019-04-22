package pl.polsl.pp.service.interfaces;


import pl.polsl.pp.model.Role;

public interface IRoleService {
    Role getByRole(String role);
    boolean saveRole(Role role);
}