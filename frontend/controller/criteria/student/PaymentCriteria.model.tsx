import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {PaymentStateCriteria} from '@/controller/criteria/student/PaymentStateCriteria.model';
import {StudentCriteria} from '@/controller/criteria/student/StudentCriteria.model';
import {CourseCriteria} from '@/controller/criteria/course/CourseCriteria.model';

export class PaymentCriteria  extends  BaseCriteria {

    public id: number | null;;

     public amount: null | number;
     public amountMin: null | number;
     public amountMax: null | number;
    public paymentDate: null | Date;
    public paymentDateFrom: null | Date;
    public paymentDateTo: null | Date;
    public student: StudentCriteria ;
    public students: Array<StudentCriteria> ;
    public course: CourseCriteria ;
    public courses: Array<CourseCriteria> ;
    public paymentState: PaymentStateCriteria ;
    public paymentStates: Array<PaymentStateCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.amount = null;
        this.amountMin = null;
        this.amountMax = null;
        this.paymentDate = null;
        this.paymentDateFrom  = null;
        this.paymentDateTo = null;
        this.student = new StudentCriteria();
        this.students = new Array<StudentCriteria>() ;
        this.course = new CourseCriteria();
        this.courses = new Array<CourseCriteria>() ;
        this.paymentState = new PaymentStateCriteria();
        this.paymentStates = new Array<PaymentStateCriteria>() ;
    }

}
