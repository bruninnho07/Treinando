package treino.Model;

import lombok.Data;
import treino.Repositories.EmployeeRepository;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TB_EMPLOYEE")
@Data
public class EmployeeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;

    @Override
    public String toString() {
        return "Id: " + Id + "\nNome: " + name + "\nIdade: " + age;
    }
}
