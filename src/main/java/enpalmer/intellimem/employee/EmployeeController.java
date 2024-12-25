package enpalmer.intellimem.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody AddEmployeeRequest addEmployeeRequest) {
        Employee employee = Employee.builder()
                .name(addEmployeeRequest.name())
                .dept(addEmployeeRequest.dept())
                .salary(addEmployeeRequest.salary())
                .build();

        return employeeRepository.save(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
