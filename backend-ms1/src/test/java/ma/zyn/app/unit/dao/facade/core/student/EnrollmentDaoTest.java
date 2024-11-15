package ma.zyn.app.unit.dao.facade.core.student;

import ma.zyn.app.bean.core.student.Enrollment;
import ma.zyn.app.dao.facade.core.student.EnrollmentDao;

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

import ma.zyn.app.bean.core.student.EnrollmentState ;
import ma.zyn.app.bean.core.student.Student ;
import ma.zyn.app.bean.core.course.Course ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EnrollmentDaoTest {

@Autowired
    private EnrollmentDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Enrollment entity = new Enrollment();
        entity.setId(id);
        underTest.save(entity);
        Enrollment loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Enrollment entity = new Enrollment();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Enrollment loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Enrollment> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Enrollment> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Enrollment given = constructSample(1);
        Enrollment saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
