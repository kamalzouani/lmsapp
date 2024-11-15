package ma.zyn.app.unit.ws.facade.admin.course;

import ma.zyn.app.bean.core.course.Resource;
import ma.zyn.app.service.impl.admin.course.ResourceAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.course.ResourceRestAdmin;
import ma.zyn.app.ws.converter.course.ResourceConverter;
import ma.zyn.app.ws.dto.course.ResourceDto;
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
public class ResourceRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ResourceAdminServiceImpl service;
    @Mock
    private ResourceConverter converter;

    @InjectMocks
    private ResourceRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllResourceTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ResourceDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ResourceDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveResourceTest() throws Exception {
        // Mock data
        ResourceDto requestDto = new ResourceDto();
        Resource entity = new Resource();
        Resource saved = new Resource();
        ResourceDto savedDto = new ResourceDto();

        // Mock the converter to return the resource object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved resource DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ResourceDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ResourceDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved resource DTO
        assertEquals(savedDto.getType(), responseBody.getType());
        assertEquals(savedDto.getUrl(), responseBody.getUrl());
        assertEquals(savedDto.getFile(), responseBody.getFile());
    }

}
