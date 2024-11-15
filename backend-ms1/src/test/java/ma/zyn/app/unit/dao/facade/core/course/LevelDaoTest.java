package ma.zyn.app.unit.dao.facade.core.course;

import ma.zyn.app.bean.core.course.Level;
import ma.zyn.app.dao.facade.core.course.LevelDao;

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


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LevelDaoTest {

@Autowired
    private LevelDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Level entity = new Level();
        entity.setCode(code);
        underTest.save(entity);
        Level loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Level loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Level entity = new Level();
        entity.setId(id);
        underTest.save(entity);
        Level loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Level entity = new Level();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Level loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Level> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Level> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Level given = constructSample(1);
        Level saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Level constructSample(int i) {
		Level given = new Level();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
