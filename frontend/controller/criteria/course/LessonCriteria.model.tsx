import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {ModuleCriteria} from '@/controller/criteria/course/ModuleCriteria.model';

export class LessonCriteria  extends  BaseCriteria {

    public id: number | null;;

    public label: string;
    public labelLike: string;
    public description: string;
    public descriptionLike: string;
    public content: string;
    public contentLike: string;
    public module: ModuleCriteria ;
    public modules: Array<ModuleCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.label = '';
        this.labelLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.content = '';
        this.contentLike = '';
        this.module = new ModuleCriteria();
        this.modules = new Array<ModuleCriteria>() ;
    }

}
