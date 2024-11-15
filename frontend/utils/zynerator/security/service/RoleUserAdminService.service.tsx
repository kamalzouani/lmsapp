import { API_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {RoleUserDto} from '@/utils/zynerator/security/model/RoleUser.model';
import {RoleUserCriteria} from '@/utils/zynerator/security/criteria/RoleUserCriteria.model';

export class RoleUserAdminService extends AbstractService<RoleUserDto, RoleUserCriteria>{

    constructor() {
        super(API_URL , 'roleUser/');
    }

};
