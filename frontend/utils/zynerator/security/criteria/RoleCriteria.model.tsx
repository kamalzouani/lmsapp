import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";


export class RoleCriteria  extends  BaseCriteria {

    public id: number | null;;

    public authority: string;
    public authorityLike: string;


    constructor() {
        super();
        this.id=null;
        this.authority = '';
        this.authorityLike = '';
    }

}
