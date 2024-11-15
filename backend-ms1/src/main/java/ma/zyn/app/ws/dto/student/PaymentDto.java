package  ma.zyn.app.ws.dto.student;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.course.CourseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto  extends AuditBaseDto {

    private BigDecimal amount  ;
    private String paymentDate ;

    private StudentDto student ;
    private CourseDto course ;
    private PaymentStateDto paymentState ;



    public PaymentDto(){
        super();
    }




    public BigDecimal getAmount(){
        return this.amount;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getPaymentDate(){
        return this.paymentDate;
    }
    public void setPaymentDate(String paymentDate){
        this.paymentDate = paymentDate;
    }


    public StudentDto getStudent(){
        return this.student;
    }

    public void setStudent(StudentDto student){
        this.student = student;
    }
    public CourseDto getCourse(){
        return this.course;
    }

    public void setCourse(CourseDto course){
        this.course = course;
    }
    public PaymentStateDto getPaymentState(){
        return this.paymentState;
    }

    public void setPaymentState(PaymentStateDto paymentState){
        this.paymentState = paymentState;
    }






}
