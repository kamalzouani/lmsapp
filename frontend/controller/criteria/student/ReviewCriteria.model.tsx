import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {StudentCriteria} from '@/controller/criteria/student/StudentCriteria.model';
import {CourseCriteria} from '@/controller/criteria/course/CourseCriteria.model';

export class ReviewCriteria  extends  BaseCriteria {

    public id: number | null;;

     public rating: null | number;
     public ratingMin: null | number;
     public ratingMax: null | number;
    public comment: string;
    public commentLike: string;
    public reviewDate: null | Date;
    public reviewDateFrom: null | Date;
    public reviewDateTo: null | Date;
    public student: StudentCriteria ;
    public students: Array<StudentCriteria> ;
    public course: CourseCriteria ;
    public courses: Array<CourseCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.rating = null;
        this.ratingMin = null;
        this.ratingMax = null;
        this.comment = '';
        this.commentLike = '';
        this.reviewDate = null;
        this.reviewDateFrom  = null;
        this.reviewDateTo = null;
        this.student = new StudentCriteria();
        this.students = new Array<StudentCriteria>() ;
        this.course = new CourseCriteria();
        this.courses = new Array<CourseCriteria>() ;
    }

}
