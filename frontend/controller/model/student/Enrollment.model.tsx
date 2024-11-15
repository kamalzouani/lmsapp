import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {EnrollmentStateDto} from '@/controller/model/student/EnrollmentState.model';
import {StudentDto} from '@/controller/model/student/Student.model';
import {CourseDto} from '@/controller/model/course/Course.model';

export class EnrollmentDto extends BaseDto{

   public enrollmentDate: null | Date;

    public student: null | StudentDto ;
    public course: null | CourseDto ;
    public enrollmentState: null | EnrollmentStateDto ;


    constructor() {
        super();
        this.enrollmentDate = null;
        this.student = null;
        this.course = null;
        this.enrollmentState = null;
        }

    getClassName() {
        return "Enrollment";
    }
}
