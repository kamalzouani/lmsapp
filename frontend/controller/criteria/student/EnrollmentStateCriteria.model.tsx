import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";


export class EnrollmentStateCriteria  extends  BaseCriteria {

    public id: number | null;;

    public code: string;
    public codeLike: string;
    public label: string;
    public labelLike: string;
    public style: string;
    public styleLike: string;


    constructor() {
        super();
        this.id=null;
        this.code = '';
        this.codeLike = '';
        this.label = '';
        this.labelLike = '';
        this.style = '';
        this.styleLike = '';
    }

}
