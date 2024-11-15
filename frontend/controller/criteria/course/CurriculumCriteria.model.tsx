import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {CourseCriteria} from '@/controller/criteria/course/CourseCriteria.model';

export class CurriculumCriteria  extends  BaseCriteria {

    public id: number | null;;

    public course: CourseCriteria ;
    public courses: Array<CourseCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.course = new CourseCriteria();
        this.courses = new Array<CourseCriteria>() ;
    }

}
