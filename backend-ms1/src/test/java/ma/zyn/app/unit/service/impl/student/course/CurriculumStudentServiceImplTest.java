package ma.zyn.app.unit.service.impl.student.course;

import ma.zyn.app.bean.core.course.Curriculum;
import ma.zyn.app.dao.facade.core.course.CurriculumDao;
import ma.zyn.app.service.impl.student.course.CurriculumStudentServiceImpl;

import ma.zyn.app.bean.core.course.Curriculum ;
import ma.zyn.app.bean.core.course.Module ;
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
class CurriculumStudentServiceImplTest {

    @Mock
    private CurriculumDao repository;
    private AutoCloseable autoCloseable;
    private CurriculumStudentServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CurriculumStudentServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCurriculum() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCurriculum() {
        // Given
        Curriculum toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCurriculum() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCurriculumById() {
        // Given
        Long idToRetrieve = 1L; // Example Curriculum ID to retrieve
        Curriculum expected = new Curriculum(); // You need to replace Curriculum with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Curriculum result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Curriculum constructSample(int i) {
		Curriculum given = new Curriculum();
        given.setCourse(new Course(1L));
        List<Module> modules = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Module element = new Module();
                                                element.setId((long)id);
                                                element.setCurriculum(new Curriculum(Long.valueOf(1)));
                                                element.setLabel("label"+id);
                                                element.setDescription("description"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setModules(modules);
        return given;
    }

}