package ma.zyn.app.unit.dao.facade.core.course;

import ma.zyn.app.bean.core.course.Curriculum;
import ma.zyn.app.dao.facade.core.course.CurriculumDao;

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

import ma.zyn.app.bean.core.course.Course ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CurriculumDaoTest {

@Autowired
    private CurriculumDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Curriculum entity = new Curriculum();
        entity.setId(id);
        underTest.save(entity);
        Curriculum loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Curriculum entity = new Curriculum();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Curriculum loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Curriculum> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Curriculum> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Curriculum given = constructSample(1);
        Curriculum saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Curriculum constructSample(int i) {
		Curriculum given = new Curriculum();
        given.setCourse(new Course(1L));
        return given;
    }

}
