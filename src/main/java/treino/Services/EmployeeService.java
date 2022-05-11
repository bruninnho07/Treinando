package treino.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import treino.Model.EmployeeModel;
import treino.Repositories.EmployeeRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;


    public void save(EmployeeModel employeeModel) {
        repository.save(employeeModel);
    }

    public List<EmployeeModel> findAll() {
        return repository.findAll();
    }

    public Optional<List<EmployeeModel>> findByName(String name) {
        return repository.findByName(name);
    }


    public void delete(EmployeeModel employeeModel) {
        repository.delete(employeeModel);
    }

    public Optional<EmployeeModel> findById(Integer id) {
        return repository.findById(id);
    }

    public boolean existsByAllInfos(String name, Integer age) {
        return repository.existsByNameAndAge(name, age);
    }
}
