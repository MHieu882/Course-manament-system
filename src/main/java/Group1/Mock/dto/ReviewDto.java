package Group1.Mock.dto;

import Group1.Mock.entity.Course;
import Group1.Mock.entity.User;
import jakarta.persistence.FetchType;

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
public class ReviewDto {
    private long reviewId;

    private String comment;

    private int rating;

    private Long coursesId;

    private long userId;

}
