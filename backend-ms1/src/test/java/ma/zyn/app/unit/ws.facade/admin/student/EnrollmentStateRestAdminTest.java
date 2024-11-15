package ma.zyn.app.unit.ws.facade.admin.student;

import ma.zyn.app.bean.core.student.EnrollmentState;
import ma.zyn.app.service.impl.admin.student.EnrollmentStateAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.student.EnrollmentStateRestAdmin;
import ma.zyn.app.ws.converter.student.EnrollmentStateConverter;
import ma.zyn.app.ws.dto.student.EnrollmentStateDto;
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
public class EnrollmentStateRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private EnrollmentStateAdminServiceImpl service;
    @Mock
    private EnrollmentStateConverter converter;

    @InjectMocks
    private EnrollmentStateRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllEnrollmentStateTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<EnrollmentStateDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<EnrollmentStateDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveEnrollmentStateTest() throws Exception {
        // Mock data
        EnrollmentStateDto requestDto = new EnrollmentStateDto();
        EnrollmentState entity = new EnrollmentState();
        EnrollmentState saved = new EnrollmentState();
        EnrollmentStateDto savedDto = new EnrollmentStateDto();

        // Mock the converter to return the enrollmentState object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved enrollmentState DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<EnrollmentStateDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        EnrollmentStateDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved enrollmentState DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
    }

}
