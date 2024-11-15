package ma.zyn.app.unit.ws.facade.admin.course;

import ma.zyn.app.bean.core.course.Module;
import ma.zyn.app.service.impl.admin.course.ModuleAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.course.ModuleRestAdmin;
import ma.zyn.app.ws.converter.course.ModuleConverter;
import ma.zyn.app.ws.dto.course.ModuleDto;
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
public class ModuleRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ModuleAdminServiceImpl service;
    @Mock
    private ModuleConverter converter;

    @InjectMocks
    private ModuleRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllModuleTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ModuleDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ModuleDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveModuleTest() throws Exception {
        // Mock data
        ModuleDto requestDto = new ModuleDto();
        Module entity = new Module();
        Module saved = new Module();
        ModuleDto savedDto = new ModuleDto();

        // Mock the converter to return the module object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved module DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ModuleDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ModuleDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved module DTO
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}