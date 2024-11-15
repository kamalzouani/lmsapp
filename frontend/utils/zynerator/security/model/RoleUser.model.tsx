import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {UserDto} from '@/utils/zynerator/security/model/User.model';
import {RoleDto} from '@/utils/zynerator/security/model/Role.model';

export class RoleUserDto extends BaseDto{

    public user: null | UserDto ;
    public role: null | RoleDto ;


    constructor() {
        super();
        this.user = new UserDto();
        this.role = new RoleDto();
        }

    getClassName() {
        return "Role user";
    }
}
