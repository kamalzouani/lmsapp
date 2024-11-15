package ma.zyn.app.unit.dao.facade.core.student;

import ma.zyn.app.bean.core.student.Review;
import ma.zyn.app.dao.facade.core.student.ReviewDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zyn.app.bean.core.student.Student ;
import ma.zyn.app.bean.core.course.Course ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ReviewDaoTest {

@Autowired
    private ReviewDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Review entity = new Review();
        entity.setId(id);
        underTest.save(entity);
        Review loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Review entity = new Review();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Review loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Review> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Review> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Review given = constructSample(1);
        Review saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
