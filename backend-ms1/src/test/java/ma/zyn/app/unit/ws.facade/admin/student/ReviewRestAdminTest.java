package ma.zyn.app.unit.ws.facade.admin.student;

import ma.zyn.app.bean.core.student.Review;
import ma.zyn.app.service.impl.admin.student.ReviewAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.student.ReviewRestAdmin;
import ma.zyn.app.ws.converter.student.ReviewConverter;
import ma.zyn.app.ws.dto.student.ReviewDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewAdminServiceImpl service;
    @Mock
    private ReviewConverter converter;

    @InjectMocks
    private ReviewRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllReviewTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ReviewDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ReviewDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveReviewTest() throws Exception {
        // Mock data
        ReviewDto requestDto = new ReviewDto();
        Review entity = new Review();
        Review saved = new Review();
        ReviewDto savedDto = new ReviewDto();

        // Mock the converter to return the review object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved review DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ReviewDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ReviewDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved review DTO
        assertEquals(savedDto.getRating(), responseBody.getRating());
        assertEquals(savedDto.getComment(), responseBody.getComment());
        assertEquals(savedDto.getReviewDate(), responseBody.getReviewDate());
    }

}
