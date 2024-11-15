package ma.zyn.app.unit.service.impl.instructor.course;

import ma.zyn.app.bean.core.course.Module;
import ma.zyn.app.dao.facade.core.course.ModuleDao;
import ma.zyn.app.service.impl.instructor.course.ModuleInstructorServiceImpl;

import ma.zyn.app.bean.core.course.Lesson ;
import ma.zyn.app.bean.core.course.Curriculum ;
import ma.zyn.app.bean.core.course.Module ;
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
class ModuleInstructorServiceImplTest {

    @Mock
    private ModuleDao repository;
    private AutoCloseable autoCloseable;
    private ModuleInstructorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ModuleInstructorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllModule() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveModule() {
        // Given
        Module toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteModule() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetModuleById() {
        // Given
        Long idToRetrieve = 1L; // Example Module ID to retrieve
        Module expected = new Module(); // You need to replace Module with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Module result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Module constructSample(int i) {
		Module given = new Module();
        given.setCurriculum(new Curriculum(1L));
        given.setLabel("label-"+i);
        given.setDescription("description-"+i);
        List<Lesson> lessons = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Lesson element = new Lesson();
                                                element.setId((long)id);
                                                element.setModule(new Module(Long.valueOf(1)));
                                                element.setLabel("label"+id);
                                                element.setDescription("description"+id);
                                                element.setContent("content"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setLessons(lessons);
        return given;
    }

}
