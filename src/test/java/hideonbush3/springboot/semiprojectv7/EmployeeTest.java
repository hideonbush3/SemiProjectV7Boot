package hideonbush3.springboot.semiprojectv7;

import hideonbush3.springboot.semiprojectv7.model.Employee;
import hideonbush3.springboot.semiprojectv7.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("전체 조회")
    void findEmployeeByAll(){
        List<Employee> emps = employeeRepository.findEmployeeByAll();

        System.out.println(emps);
    }

    @Test
    @DisplayName("이름, 성 조회")
    void findFirstNameAndLastNameByEmployee() {
        List<Object[]> emps = employeeRepository.findFirstNameAndLastNameByEmployee();

        for(Object[] emp : emps) System.out.println(emp[0] + " / " + emp[1]);
    }

}
