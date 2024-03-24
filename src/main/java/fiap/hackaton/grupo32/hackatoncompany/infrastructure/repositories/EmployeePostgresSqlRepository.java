package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories;

import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeePostgresSqlRepository extends JpaRepository<EmployeeEntity, UUID> {
    Optional<EmployeeEntity> findByCorporateId(String name);
}
