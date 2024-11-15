import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {LessonCriteria} from '@/controller/criteria/course/LessonCriteria.model';

export class ResourceCriteria  extends  BaseCriteria {

    public id: number | null;;

    public type: string;
    public typeLike: string;
    public url: string;
    public urlLike: string;
    public file: string;
    public fileLike: string;
    public lesson: LessonCriteria ;
    public lessons: Array<LessonCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.type = '';
        this.typeLike = '';
        this.url = '';
        this.urlLike = '';
        this.file = '';
        this.fileLike = '';
        this.lesson = new LessonCriteria();
        this.lessons = new Array<LessonCriteria>() ;
    }

}
