import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";


import {RoleDto} from "app/zynerator/dto/RoleDto.model";
export class InstructorDto extends BaseDto{

    public bio: string;

   public credentialsNonExpired: boolean;

   public accountNonExpired: boolean;

    public username: string;

   public passwordChanged: boolean;

   public accountNonLocked: boolean;

    public password: string;

    public email: string;

   public enabled: boolean;


    public roles: RoleDto[];

    constructor() {
        super();
        this.bio = '';
        this.credentialsNonExpired = null;
        this.accountNonExpired = null;
        this.username = '';
        this.passwordChanged = null;
        this.accountNonLocked = null;
        this.password = '';
        this.email = '';
        this.enabled = null;
        }

    getClassName() {
        return "Instructor";
    }
}
