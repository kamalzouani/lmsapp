package ma.zyn.app.unit.service.impl.instructor.instructor;

import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.dao.facade.core.instructor.InstructorDao;
import ma.zyn.app.service.impl.instructor.instructor.InstructorInstructorServiceImpl;

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
class InstructorInstructorServiceImplTest {

    @Mock
    private InstructorDao repository;
    private AutoCloseable autoCloseable;
    private InstructorInstructorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new InstructorInstructorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllInstructor() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveInstructor() {
        // Given
        Instructor toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteInstructor() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetInstructorById() {
        // Given
        Long idToRetrieve = 1L; // Example Instructor ID to retrieve
        Instructor expected = new Instructor(); // You need to replace Instructor with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Instructor result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Instructor constructSample(int i) {
		Instructor given = new Instructor();
        given.setBio("bio-"+i);
        given.setCredentialsNonExpired(false);
        given.setAccountNonExpired(false);
        given.setUsername("username-"+i);
        given.setPasswordChanged(false);
        given.setAccountNonLocked(false);
        given.setPassword("password-"+i);
        given.setEmail("email-"+i);
        given.setEnabled(false);
        return given;
    }

}
