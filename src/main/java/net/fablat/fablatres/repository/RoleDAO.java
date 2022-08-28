package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.Role;

public interface RoleDAO extends GenericDAO<Role, Integer> {

	Role findByName(String name);
}
