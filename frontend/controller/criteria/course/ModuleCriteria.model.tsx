import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {CurriculumCriteria} from '@/controller/criteria/course/CurriculumCriteria.model';

export class ModuleCriteria  extends  BaseCriteria {

    public id: number | null;;

    public label: string;
    public labelLike: string;
    public description: string;
    public descriptionLike: string;
    public curriculum: CurriculumCriteria ;
    public curriculums: Array<CurriculumCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.label = '';
        this.labelLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.curriculum = new CurriculumCriteria();
        this.curriculums = new Array<CurriculumCriteria>() ;
    }

}
