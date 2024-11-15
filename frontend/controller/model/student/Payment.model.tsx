import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {PaymentStateDto} from '@/controller/model/student/PaymentState.model';
import {StudentDto} from '@/controller/model/student/Student.model';
import {CourseDto} from '@/controller/model/course/Course.model';

export class PaymentDto extends BaseDto{

    public amount: null | number;

   public paymentDate: null | Date;

    public student: null | StudentDto ;
    public course: null | CourseDto ;
    public paymentState: null | PaymentStateDto ;


    constructor() {
        super();
        this.amount = null;
        this.paymentDate = null;
        this.student = null;
        this.course = null;
        this.paymentState = null;
        }

    getClassName() {
        return "Payment";
    }
}
