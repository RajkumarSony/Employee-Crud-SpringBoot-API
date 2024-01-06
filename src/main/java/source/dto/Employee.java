package source.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    Long employeeId;
    String employeeName;
    String department;
}
