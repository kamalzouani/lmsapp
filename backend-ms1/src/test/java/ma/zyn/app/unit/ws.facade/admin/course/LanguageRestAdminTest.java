package ma.zyn.app.unit.ws.facade.admin.course;

import ma.zyn.app.bean.core.course.Language;
import ma.zyn.app.service.impl.admin.course.LanguageAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.course.LanguageRestAdmin;
import ma.zyn.app.ws.converter.course.LanguageConverter;
import ma.zyn.app.ws.dto.course.LanguageDto;
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
public class LanguageRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private LanguageAdminServiceImpl service;
    @Mock
    private LanguageConverter converter;

    @InjectMocks
    private LanguageRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllLanguageTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<LanguageDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<LanguageDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveLanguageTest() throws Exception {
        // Mock data
        LanguageDto requestDto = new LanguageDto();
        Language entity = new Language();
        Language saved = new Language();
        LanguageDto savedDto = new LanguageDto();

        // Mock the converter to return the language object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved language DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<LanguageDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        LanguageDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved language DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
    }

}