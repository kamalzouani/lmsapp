package ma.zyn.app.unit.ws.facade.admin.course;

import ma.zyn.app.bean.core.course.Lesson;
import ma.zyn.app.service.impl.admin.course.LessonAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.course.LessonRestAdmin;
import ma.zyn.app.ws.converter.course.LessonConverter;
import ma.zyn.app.ws.dto.course.LessonDto;
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
public class LessonRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private LessonAdminServiceImpl service;
    @Mock
    private LessonConverter converter;

    @InjectMocks
    private LessonRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllLessonTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<LessonDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<LessonDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveLessonTest() throws Exception {
        // Mock data
        LessonDto requestDto = new LessonDto();
        Lesson entity = new Lesson();
        Lesson saved = new Lesson();
        LessonDto savedDto = new LessonDto();

        // Mock the converter to return the lesson object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved lesson DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<LessonDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        LessonDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved lesson DTO
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getContent(), responseBody.getContent());
    }

}
