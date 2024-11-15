import { API_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ModelPermissionDto} from '@/utils/zynerator/security/model/ModelPermission.model';
import {ModelPermissionCriteria} from '@/utils/zynerator/security/criteria/ModelPermissionCriteria.model';

export class ModelPermissionAdminService extends AbstractService<ModelPermissionDto, ModelPermissionCriteria>{

    constructor() {
        super(API_URL , 'modelPermission/');
    }

};
