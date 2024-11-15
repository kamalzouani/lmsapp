import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";


export class StudentCriteria  extends  BaseCriteria {

    public id: number | null;;

    public credentialsNonExpired: null | boolean;
    public accountNonExpired: null | boolean;
    public username: string;
    public usernameLike: string;
    public passwordChanged: null | boolean;
    public accountNonLocked: null | boolean;
    public password: string;
    public passwordLike: string;
    public email: string;
    public emailLike: string;
    public enabled: null | boolean;


    constructor() {
        super();
        this.id=null;
        this.credentialsNonExpired = null;
        this.accountNonExpired = null;
        this.username = '';
        this.usernameLike = '';
        this.passwordChanged = null;
        this.accountNonLocked = null;
        this.password = '';
        this.passwordLike = '';
        this.email = '';
        this.emailLike = '';
        this.enabled = null;
    }

}
