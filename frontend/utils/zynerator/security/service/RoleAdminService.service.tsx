import { API_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {RoleDto} from '@/utils/zynerator/security/model/Role.model';
import {RoleCriteria} from '@/utils/zynerator/security/criteria/RoleCriteria.model';

export class RoleAdminService extends AbstractService<RoleDto, RoleCriteria>{

    constructor() {
        super(API_URL , 'role/');
    }

};
