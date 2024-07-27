package Group1.Mock.controller;

import Group1.Mock.dto.ReviewDto;
import Group1.Mock.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    void testGetAllReviews() throws Exception {
//        // Mock a successful response from ReviewService
//        when(reviewService.getAllReviews(any())).thenReturn(ResponseEntity.ok(List.of(new ReviewDto(1, "Great course!", 5, 1L, 1L))));
//
//        mockMvc.perform(get("/review")
//                        .param("page", "0")
//                        .param("size", "10")
//                        .with(SecurityMockMvcRequestPostProcessors.user("student").roles("STUDENT"))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comment").value("Great course!"));
//
//        verify(reviewService, times(1)).getAllReviews(any());
    }

    @Test
    void testCreateReview() throws Exception {
        ReviewDto reviewDto = new ReviewDto(1, "Great course!", 5, 1L, 1L);
        when(reviewService.createReview(any(ReviewDto.class))).thenReturn(reviewDto);

        mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reviewId\":1,\"comment\":\"Great course!\",\"rating\":5,\"coursesId\":1,\"userId\":1}")
                        .with(SecurityMockMvcRequestPostProcessors.user("student").roles("STUDENT"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("Great course!"));

        verify(reviewService, times(1)).createReview(any(ReviewDto.class));
    }
}
