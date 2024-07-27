package Group1.Mock.dto;

import Group1.Mock.entity.Category;
import Group1.Mock.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private Long courseId;

    private String courseName;

    private String description;

    private double price;

    private Date createdAt;

    private Date updatedAt;

    private boolean isApproved;

    private String courseLevel;

    private String language;

    private Set<Long> categoryIds;

    private Set<Long> instructorIds;

    private Set<Long> reviewIds;

}
