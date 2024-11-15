import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {UserDto} from '@/utils/zynerator/security/model/User.model';
import {ModelPermissionDto} from '@/utils/zynerator/security/model/ModelPermission.model';
import {ActionPermissionDto} from '@/utils/zynerator/security/model/ActionPermission.model';

export class ModelPermissionUserDto extends BaseDto{

   public value: boolean |null;

    public subAttribute: string;

    public actionPermission: null | ActionPermissionDto ;
    public modelPermission: null | ModelPermissionDto ;
    public user: null | UserDto ;


    constructor() {
        super();
        this.value = null;
        this.subAttribute = '';
        this.actionPermission = null;
        this.modelPermission = null;
        this.user = null;
        }

    getClassName() {
        return "Model permission user";
    }
}
