package ma.zyn.app.unit.service.impl.instructor.course;

import ma.zyn.app.bean.core.course.Lesson;
import ma.zyn.app.dao.facade.core.course.LessonDao;
import ma.zyn.app.service.impl.instructor.course.LessonInstructorServiceImpl;

import ma.zyn.app.bean.core.course.Lesson ;
import ma.zyn.app.bean.core.course.Module ;
import ma.zyn.app.bean.core.course.Resource ;
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
class LessonInstructorServiceImplTest {

    @Mock
    private LessonDao repository;
    private AutoCloseable autoCloseable;
    private LessonInstructorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new LessonInstructorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllLesson() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveLesson() {
        // Given
        Lesson toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteLesson() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetLessonById() {
        // Given
        Long idToRetrieve = 1L; // Example Lesson ID to retrieve
        Lesson expected = new Lesson(); // You need to replace Lesson with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Lesson result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Lesson constructSample(int i) {
		Lesson given = new Lesson();
        given.setModule(new Module(1L));
        given.setLabel("label-"+i);
        given.setDescription("description-"+i);
        given.setContent("content-"+i);
        List<Resource> resources = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Resource element = new Resource();
                                                element.setId((long)id);
                                                element.setLesson(new Lesson(Long.valueOf(1)));
                                                element.setType("type"+id);
                                                element.setUrl("url"+id);
                                                element.setFile("file"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setResources(resources);
        return given;
    }

}
