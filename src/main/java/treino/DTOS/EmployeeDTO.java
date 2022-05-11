package treino.DTOS;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeDTO {
    @NotBlank
    private String name;
    @NotNull(message = "Por favor. Informe sua idade.")
    private Integer age;
}
