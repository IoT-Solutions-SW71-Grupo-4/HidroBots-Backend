package org.hidrobots.platform.iam.infrastructure.persistence.jpa.repositories;


import org.hidrobots.platform.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // busca en la base de datos un usuario por su nombre de usuario
    Optional<User> findByEmail(String email);
    
    // verifica si existe un usuario con el nombre de usuario
    boolean existsByEmail(String email);
}
