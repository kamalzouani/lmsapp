package ma.zyn.app.unit.ws.facade.admin.course;

import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.service.impl.admin.course.CourseAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.course.CourseRestAdmin;
import ma.zyn.app.ws.converter.course.CourseConverter;
import ma.zyn.app.ws.dto.course.CourseDto;
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
public class CourseRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private CourseAdminServiceImpl service;
    @Mock
    private CourseConverter converter;

    @InjectMocks
    private CourseRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllCourseTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<CourseDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<CourseDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveCourseTest() throws Exception {
        // Mock data
        CourseDto requestDto = new CourseDto();
        Course entity = new Course();
        Course saved = new Course();
        CourseDto savedDto = new CourseDto();

        // Mock the converter to return the course object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved course DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<CourseDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        CourseDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved course DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getStartDate(), responseBody.getStartDate());
        assertEquals(savedDto.getEndDate(), responseBody.getEndDate());
        assertEquals(savedDto.getDuration(), responseBody.getDuration());
        assertEquals(savedDto.getPrice(), responseBody.getPrice());
        assertEquals(savedDto.getRequirements(), responseBody.getRequirements());
        assertEquals(savedDto.getLearningOutcomes(), responseBody.getLearningOutcomes());
    }

}
