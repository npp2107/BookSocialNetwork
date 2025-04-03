package npp.booksocialnetwork.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import npp.booksocialnetwork.identity.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
