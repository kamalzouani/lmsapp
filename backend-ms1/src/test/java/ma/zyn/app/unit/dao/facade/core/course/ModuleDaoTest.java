package ma.zyn.app.unit.dao.facade.core.course;

import ma.zyn.app.bean.core.course.Module;
import ma.zyn.app.dao.facade.core.course.ModuleDao;

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

import ma.zyn.app.bean.core.course.Curriculum ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ModuleDaoTest {

@Autowired
    private ModuleDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Module entity = new Module();
        entity.setId(id);
        underTest.save(entity);
        Module loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Module entity = new Module();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Module loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Module> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Module> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Module given = constructSample(1);
        Module saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Module constructSample(int i) {
		Module given = new Module();
        given.setCurriculum(new Curriculum(1L));
        given.setLabel("label-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
