package Group1.Mock.dto;

import Group1.Mock.entity.Course;
import Group1.Mock.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {

    private long enrollmentId;

    private double grade;

    private boolean status;

    private Date enrollmentDate;

    private long userId;

    private long courseId;
}
