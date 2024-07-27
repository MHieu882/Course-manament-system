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
public class CategoryDto {

    private Long categoryId;


    private String categoryName;


    private String description;


    private Set<Long> courseId ;
}
