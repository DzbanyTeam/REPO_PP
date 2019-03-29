package pl.polsl.pp.service;


import pl.polsl.pp.model.Role;

public interface RoleServiceInterface {
    Role getByRole(String role);
    boolean saveRole(Role role);
}