package ma.zyn.app.unit.dao.facade.core.course;

import ma.zyn.app.bean.core.course.Resource;
import ma.zyn.app.dao.facade.core.course.ResourceDao;

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

import ma.zyn.app.bean.core.course.Lesson ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ResourceDaoTest {

@Autowired
    private ResourceDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Resource entity = new Resource();
        entity.setId(id);
        underTest.save(entity);
        Resource loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Resource entity = new Resource();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Resource loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Resource> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Resource> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Resource given = constructSample(1);
        Resource saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Resource constructSample(int i) {
		Resource given = new Resource();
        given.setLesson(new Lesson(1L));
        given.setType("type-"+i);
        given.setUrl("url-"+i);
        given.setFile("file-"+i);
        return given;
    }

}
