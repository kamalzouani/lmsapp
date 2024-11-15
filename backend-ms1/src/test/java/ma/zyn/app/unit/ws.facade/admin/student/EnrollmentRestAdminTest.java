package ma.zyn.app.unit.ws.facade.admin.student;

import ma.zyn.app.bean.core.student.Enrollment;
import ma.zyn.app.service.impl.admin.student.EnrollmentAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.student.EnrollmentRestAdmin;
import ma.zyn.app.ws.converter.student.EnrollmentConverter;
import ma.zyn.app.ws.dto.student.EnrollmentDto;
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
public class EnrollmentRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private EnrollmentAdminServiceImpl service;
    @Mock
    private EnrollmentConverter converter;

    @InjectMocks
    private EnrollmentRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllEnrollmentTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<EnrollmentDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<EnrollmentDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveEnrollmentTest() throws Exception {
        // Mock data
        EnrollmentDto requestDto = new EnrollmentDto();
        Enrollment entity = new Enrollment();
        Enrollment saved = new Enrollment();
        EnrollmentDto savedDto = new EnrollmentDto();

        // Mock the converter to return the enrollment object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved enrollment DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<EnrollmentDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        EnrollmentDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved enrollment DTO
        assertEquals(savedDto.getEnrollmentDate(), responseBody.getEnrollmentDate());
    }

}
