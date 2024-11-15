package ma.zyn.app.unit.service.impl.student.course;

import ma.zyn.app.bean.core.course.Level;
import ma.zyn.app.dao.facade.core.course.LevelDao;
import ma.zyn.app.service.impl.student.course.LevelStudentServiceImpl;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class LevelStudentServiceImplTest {

    @Mock
    private LevelDao repository;
    private AutoCloseable autoCloseable;
    private LevelStudentServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new LevelStudentServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllLevel() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveLevel() {
        // Given
        Level toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteLevel() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetLevelById() {
        // Given
        Long idToRetrieve = 1L; // Example Level ID to retrieve
        Level expected = new Level(); // You need to replace Level with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Level result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Level constructSample(int i) {
		Level given = new Level();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
