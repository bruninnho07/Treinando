package treino.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import treino.Model.EmployeeModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer> {

    Optional<List<EmployeeModel>> findByName(String name);

    boolean existsByNameAndAge(String name, Integer age);
}
