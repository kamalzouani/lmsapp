package ma.zyn.app.unit.dao.facade.core.course;

import ma.zyn.app.bean.core.course.Lesson;
import ma.zyn.app.dao.facade.core.course.LessonDao;

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

import ma.zyn.app.bean.core.course.Module ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LessonDaoTest {

@Autowired
    private LessonDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Lesson entity = new Lesson();
        entity.setId(id);
        underTest.save(entity);
        Lesson loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Lesson entity = new Lesson();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Lesson loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Lesson> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Lesson> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Lesson given = constructSample(1);
        Lesson saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Lesson constructSample(int i) {
		Lesson given = new Lesson();
        given.setModule(new Module(1L));
        given.setLabel("label-"+i);
        given.setDescription("description-"+i);
        given.setContent("content-"+i);
        return given;
    }

}
