import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";


export class UserCriteria  extends  BaseCriteria {

    public id: number | null;;

    public credentialsNonExpired: null | boolean;
    public enabled: null | boolean;
    public email: string;
    public emailLike: string;
    public accountNonExpired: null | boolean;
    public accountNonLocked: null | boolean;
    public username: string;
    public usernameLike: string;
    public password: string;
    public passwordLike: string;
    public passwordChanged: null | boolean;
    public lastName: string;
    public lastNameLike: string;
    public firstName: string;
    public firstNameLike: string;
    public phone: string;
    public phoneLike: string;


    constructor() {
        super();
        this.id=null;
        this.credentialsNonExpired = null;
        this.enabled = null;
        this.email = '';
        this.emailLike = '';
        this.accountNonExpired = null;
        this.accountNonLocked = null;
        this.username = '';
        this.usernameLike = '';
        this.password = '';
        this.passwordLike = '';
        this.passwordChanged = null;
        this.lastName = '';
        this.lastNameLike = '';
        this.firstName = '';
        this.firstNameLike = '';
        this.phone = '';
        this.phoneLike = '';
    }

}
