package ma.zyn.app.unit.dao.facade.core.student;

import ma.zyn.app.bean.core.student.EnrollmentState;
import ma.zyn.app.dao.facade.core.student.EnrollmentStateDao;

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
public class EnrollmentStateDaoTest {

@Autowired
    private EnrollmentStateDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        EnrollmentState entity = new EnrollmentState();
        entity.setCode(code);
        underTest.save(entity);
        EnrollmentState loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        EnrollmentState loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        EnrollmentState entity = new EnrollmentState();
        entity.setId(id);
        underTest.save(entity);
        EnrollmentState loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        EnrollmentState entity = new EnrollmentState();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        EnrollmentState loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<EnrollmentState> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<EnrollmentState> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        EnrollmentState given = constructSample(1);
        EnrollmentState saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private EnrollmentState constructSample(int i) {
		EnrollmentState given = new EnrollmentState();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
