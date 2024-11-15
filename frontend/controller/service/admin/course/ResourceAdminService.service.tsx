import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ResourceDto} from '@/controller/model/course/Resource.model';
import {ResourceCriteria} from '@/controller/criteria/course/ResourceCriteria.model';

export class ResourceAdminService extends AbstractService<ResourceDto, ResourceCriteria>{

    constructor() {
        super(ADMIN_URL , 'resource/');
    }

};
