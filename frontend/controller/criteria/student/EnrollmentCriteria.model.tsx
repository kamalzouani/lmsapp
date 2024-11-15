import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {EnrollmentStateCriteria} from '@/controller/criteria/student/EnrollmentStateCriteria.model';
import {StudentCriteria} from '@/controller/criteria/student/StudentCriteria.model';
import {CourseCriteria} from '@/controller/criteria/course/CourseCriteria.model';

export class EnrollmentCriteria  extends  BaseCriteria {

    public id: number | null;;

    public enrollmentDate: null | Date;
    public enrollmentDateFrom: null | Date;
    public enrollmentDateTo: null | Date;
    public student: StudentCriteria ;
    public students: Array<StudentCriteria> ;
    public course: CourseCriteria ;
    public courses: Array<CourseCriteria> ;
    public enrollmentState: EnrollmentStateCriteria ;
    public enrollmentStates: Array<EnrollmentStateCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.enrollmentDate = null;
        this.enrollmentDateFrom  = null;
        this.enrollmentDateTo = null;
        this.student = new StudentCriteria();
        this.students = new Array<StudentCriteria>() ;
        this.course = new CourseCriteria();
        this.courses = new Array<CourseCriteria>() ;
        this.enrollmentState = new EnrollmentStateCriteria();
        this.enrollmentStates = new Array<EnrollmentStateCriteria>() ;
    }

}
