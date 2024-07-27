package Group1.Mock.dto;

import Group1.Mock.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Set<Long> courseId ;
    private Long userId;
    private Double total;
    private String status;


}
