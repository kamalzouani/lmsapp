package ma.zyn.app.unit.service.impl.admin.student;

import ma.zyn.app.bean.core.student.Review;
import ma.zyn.app.dao.facade.core.student.ReviewDao;
import ma.zyn.app.service.impl.admin.student.ReviewAdminServiceImpl;

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
class ReviewAdminServiceImplTest {

    @Mock
    private ReviewDao repository;
    private AutoCloseable autoCloseable;
    private ReviewAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ReviewAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllReview() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveReview() {
        // Given
        Review toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteReview() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetReviewById() {
        // Given
        Long idToRetrieve = 1L; // Example Review ID to retrieve
        Review expected = new Review(); // You need to replace Review with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Review result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Review constructSample(int i) {
		Review given = new Review();
        given.setStudent(new Student(1L));
        given.setCourse(new Course(1L));
        given.setRating(BigDecimal.TEN);
        given.setComment("comment-"+i);
        given.setReviewDate(LocalDateTime.now());
        return given;
    }

}
