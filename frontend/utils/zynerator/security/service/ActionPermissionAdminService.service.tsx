import { ADMIN_URL, API_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ActionPermissionDto} from '@/utils/zynerator/security/model/ActionPermission.model';
import {ActionPermissionCriteria} from '@/utils/zynerator/security/criteria/ActionPermissionCriteria.model';

export class ActionPermissionAdminService extends AbstractService<ActionPermissionDto, ActionPermissionCriteria>{

    constructor() {
        super(API_URL , 'actionPermission/');
    }

};
