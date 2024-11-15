package ma.zyn.app.unit.service.impl.instructor.course;

import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.dao.facade.core.course.CourseDao;
import ma.zyn.app.service.impl.instructor.course.CourseInstructorServiceImpl;

import ma.zyn.app.bean.core.course.Category ;
import ma.zyn.app.bean.core.instructor.Instructor ;
import ma.zyn.app.bean.core.course.Curriculum ;
import ma.zyn.app.bean.core.course.Language ;
import ma.zyn.app.bean.core.course.Level ;
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
class CourseInstructorServiceImplTest {

    @Mock
    private CourseDao repository;
    private AutoCloseable autoCloseable;
    private CourseInstructorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CourseInstructorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCourse() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCourse() {
        // Given
        Course toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCourse() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCourseById() {
        // Given
        Long idToRetrieve = 1L; // Example Course ID to retrieve
        Course expected = new Course(); // You need to replace Course with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Course result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Course constructSample(int i) {
		Course given = new Course();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setDescription("description-"+i);
        given.setStartDate(LocalDateTime.now());
        given.setEndDate(LocalDateTime.now());
        given.setDuration(BigDecimal.TEN);
        given.setPrice(BigDecimal.TEN);
        given.setInstructor(new Instructor(1L));
        given.setCategory(new Category(1L));
        given.setLevel(new Level(1L));
        given.setLanguage(new Language(1L));
        given.setRequirements("requirements-"+i);
        given.setLearningOutcomes("learningOutcomes-"+i);
        given.setCurriculum(new Curriculum(1L));
        return given;
    }

}
