package Group1.Mock.service;

import Group1.Mock.entity.Review;
import Group1.Mock.dto.ReviewDto;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review review1;
    private Review review2;
    private List<Review> reviews;
    private Page<Review> page;
    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        review1 = new Review();
        review1.setReviewId(1L);
        review1.setComment("Great course!");
        review1.setRating(4);

        review2 = new Review();
        review2.setReviewId(2L);
        review2.setComment("Not bad.");
        review2.setRating(3);

        reviews = Arrays.asList(review1, review2);
        page = new PageImpl<>(reviews);
        pageable = Pageable.unpaged();
    }



}