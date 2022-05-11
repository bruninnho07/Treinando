package treino.Controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import treino.DTOS.EmployeeDTO;
import treino.Exceptions.AgeException;
import treino.Exceptions.AlreadyExistsInfos;
import treino.Exceptions.NotFoundException;
import treino.Model.EmployeeModel;
import treino.Services.EmployeeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/employees")
public class EmployeeController {
    //INJETAR O SERVICE
    @Autowired
    private EmployeeService service;


    //POSTAR FUNCIONÁRIO
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.OK)
    public void saveEmployee(@RequestBody @Valid EmployeeDTO dto){
        if(service.existsByAllInfos(dto.getName(), dto.getAge())){
            throw new AlreadyExistsInfos("Já existe funcionários com todas essas informações!", HttpStatus.CONFLICT);
        }
        if(dto.getAge() < 18){
            throw new AgeException("A pessoa tem que ter mais de 18 anos.", HttpStatus.CONFLICT);
        }
        //REGRA DA EMPRESA
        if(dto.getAge() > 70){
            throw new AgeException("A empresa não permite pessoas com mais de 70 anos.", HttpStatus.CONFLICT);
        }
        var employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(dto, employeeModel);
        service.save(employeeModel);
        System.out.println("Funcionário criado: \n" +
                employeeModel);
    }

    //OBTER TODOS FUNCIONARIOS
    @GetMapping("/")
    public List<EmployeeModel> getAll(){
        return service.findAll();
    }

    //Obter pelo Nome
    @GetMapping("/filtrar/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeModel> getByName(@PathVariable(value = "name") String name){
        Optional<List<EmployeeModel>> employeeModelOptional = service.findByName(name);
        //VERIFICANDO SE O FUNCIONÁRIO EXISTE
        if(!employeeModelOptional.isPresent()){
            throw new NotFoundException("Não achamos funcionários com este nome", HttpStatus.NOT_FOUND);
        }
        return employeeModelOptional.get();
    }

    //Deletar Funcionário
    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable(value = "id") Integer id){
        Optional<EmployeeModel> employeeModelOptional = service.findById(id);
        //VERIFICANDO SE O FUNCIONÁRIO EXISTE
        if(!employeeModelOptional.isPresent()){
            throw new NotFoundException("Não achamos funcionários com este id", HttpStatus.NOT_FOUND);
        }
        service.delete(employeeModelOptional.get());
        System.out.println("Deletado.");
    }

    //Deletar Funcionário
    @DeleteMapping("/deletar/todos")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllEmployees(){
        List<EmployeeModel> getAll = service.findAll();
        for (EmployeeModel employeeModel : getAll) {
            service.delete(employeeModel);
        }
        System.out.println("Deletados.");
    }

    //Atualizar Dados
    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@PathVariable(value = "id") Integer id, @RequestBody @Valid EmployeeDTO dto){
        Optional<EmployeeModel> employeeModelOptional = service.findById(id);
        //VERIFICANDO SE O FUNCIONÁRIO EXISTE
        if(!employeeModelOptional.isPresent()){
            throw new NotFoundException("Não achamos funcionários com este id", HttpStatus.NOT_FOUND);
        }
        var employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(dto, employeeModel);
        employeeModel.setId(employeeModelOptional.get().getId());
        service.save(employeeModel);
        System.out.println("Atualizado.");
    }
}
