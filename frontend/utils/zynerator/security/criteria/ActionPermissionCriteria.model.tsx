import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";


export class ActionPermissionCriteria  extends  BaseCriteria {

    public id: number | null;;

    public reference: string;
    public referenceLike: string;
    public libelle: string;
    public libelleLike: string;


    constructor() {
        super();
        this.id=null;
        this.reference = '';
        this.referenceLike = '';
        this.libelle = '';
        this.libelleLike = '';
    }

}
