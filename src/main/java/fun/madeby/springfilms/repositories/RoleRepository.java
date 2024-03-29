package fun.madeby.springfilms.repositories;

import fun.madeby.springfilms.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
	Role save(Role role);
}
