package ma.zyn.app.unit.dao.facade.core.student;

import ma.zyn.app.bean.core.student.Payment;
import ma.zyn.app.dao.facade.core.student.PaymentDao;

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

import ma.zyn.app.bean.core.student.PaymentState ;
import ma.zyn.app.bean.core.student.Student ;
import ma.zyn.app.bean.core.course.Course ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PaymentDaoTest {

@Autowired
    private PaymentDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Payment entity = new Payment();
        entity.setId(id);
        underTest.save(entity);
        Payment loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Payment entity = new Payment();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Payment loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Payment> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Payment> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Payment given = constructSample(1);
        Payment saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Payment constructSample(int i) {
		Payment given = new Payment();
        given.setStudent(new Student(1L));
        given.setCourse(new Course(1L));
        given.setAmount(BigDecimal.TEN);
        given.setPaymentDate(LocalDateTime.now());
        given.setPaymentState(new PaymentState(1L));
        return given;
    }

}
