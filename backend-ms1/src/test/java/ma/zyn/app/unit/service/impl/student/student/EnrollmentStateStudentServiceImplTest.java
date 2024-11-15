package ma.zyn.app.unit.service.impl.student.student;

import ma.zyn.app.bean.core.student.EnrollmentState;
import ma.zyn.app.dao.facade.core.student.EnrollmentStateDao;
import ma.zyn.app.service.impl.student.student.EnrollmentStateStudentServiceImpl;

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
class EnrollmentStateStudentServiceImplTest {

    @Mock
    private EnrollmentStateDao repository;
    private AutoCloseable autoCloseable;
    private EnrollmentStateStudentServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new EnrollmentStateStudentServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllEnrollmentState() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveEnrollmentState() {
        // Given
        EnrollmentState toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteEnrollmentState() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetEnrollmentStateById() {
        // Given
        Long idToRetrieve = 1L; // Example EnrollmentState ID to retrieve
        EnrollmentState expected = new EnrollmentState(); // You need to replace EnrollmentState with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        EnrollmentState result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private EnrollmentState constructSample(int i) {
		EnrollmentState given = new EnrollmentState();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
