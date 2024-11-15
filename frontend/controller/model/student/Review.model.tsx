import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {StudentDto} from '@/controller/model/student/Student.model';
import {CourseDto} from '@/controller/model/course/Course.model';

export class ReviewDto extends BaseDto{

    public rating: null | number;

    public comment: string;

   public reviewDate: null | Date;

    public student: null | StudentDto ;
    public course: null | CourseDto ;


    constructor() {
        super();
        this.rating = null;
        this.comment = '';
        this.reviewDate = null;
        this.student = null;
        this.course = null;
        }

    getClassName() {
        return "Review";
    }
}
