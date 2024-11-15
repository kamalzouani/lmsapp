import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {RoleDto} from '@/utils/zynerator/security/model/Role.model';
import {ModelPermissionUserDto} from '@/utils/zynerator/security/model/ModelPermissionUser.model';
import {ModelPermissionDto} from '@/utils/zynerator/security/model/ModelPermission.model';
import {RoleUserDto} from '@/utils/zynerator/security/model/RoleUser.model';
import {ActionPermissionDto} from '@/utils/zynerator/security/model/ActionPermission.model';

export class UserDto extends BaseDto{

   public credentialsNonExpired: boolean | null;

   public enabled: boolean | null;

    public email: string;

   public accountNonExpired: boolean | null;

   public accountNonLocked: boolean | null;

    public username: string;

    public password: string;

   public passwordChanged: boolean | null;

   public lastName: string;

   public firstName: string;

   public phone: string;

     public roleUsers: Array<RoleUserDto>;
     public modelPermissionUsers: Array<ModelPermissionUserDto>;


    constructor() {
        super();
        this.credentialsNonExpired = null;
        this.enabled = null;
        this.email = '';
        this.accountNonExpired = null;
        this.accountNonLocked = null;
        this.username = '';
        this.password = '';
        this.passwordChanged = null;
        this.lastName = '';
        this.firstName = '';
        this.phone = '';
        this.roleUsers = new Array<RoleUserDto>();
        this.modelPermissionUsers = new Array<ModelPermissionUserDto>();
        }

    getClassName() {
        return "User";
    }
}
