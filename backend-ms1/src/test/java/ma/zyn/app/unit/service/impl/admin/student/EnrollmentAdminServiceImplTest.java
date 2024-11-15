package ma.zyn.app.unit.service.impl.admin.student;

import ma.zyn.app.bean.core.student.Enrollment;
import ma.zyn.app.dao.facade.core.student.EnrollmentDao;
import ma.zyn.app.service.impl.admin.student.EnrollmentAdminServiceImpl;

import ma.zyn.app.bean.core.student.EnrollmentState ;
import ma.zyn.app.bean.core.student.Student ;
import ma.zyn.app.bean.core.course.Course ;
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
class EnrollmentAdminServiceImplTest {

    @Mock
    private EnrollmentDao repository;
    private AutoCloseable autoCloseable;
    private EnrollmentAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new EnrollmentAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllEnrollment() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveEnrollment() {
        // Given
        Enrollment toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteEnrollment() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetEnrollmentById() {
        // Given
        Long idToRetrieve = 1L; // Example Enrollment ID to retrieve
        Enrollment expected = new Enrollment(); // You need to replace Enrollment with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Enrollment result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Enrollment constructSample(int i) {
		Enrollment given = new Enrollment();
        given.setStudent(new Student(1L));
        given.setCourse(new Course(1L));
        given.setEnrollmentDate(LocalDateTime.now());
        given.setEnrollmentState(new EnrollmentState(1L));
        return given;
    }

}
