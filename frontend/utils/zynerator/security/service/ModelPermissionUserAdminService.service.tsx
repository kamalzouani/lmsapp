import { API_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ModelPermissionUserDto} from '@/utils/zynerator/security/model/ModelPermissionUser.model';
import {ModelPermissionUserCriteria} from '@/utils/zynerator/security/criteria/ModelPermissionUserCriteria.model';

export class ModelPermissionUserAdminService extends AbstractService<ModelPermissionUserDto, ModelPermissionUserCriteria>{

    constructor() {
        super(API_URL , 'modelPermissionUser/');
    }

};
