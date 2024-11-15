import { STUDENT_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ResourceDto} from '@/controller/model/course/Resource.model';
import {ResourceCriteria} from '@/controller/criteria/course/ResourceCriteria.model';

export class ResourceStudentService extends AbstractService<ResourceDto, ResourceCriteria>{

    constructor() {
        super(STUDENT_URL , 'resource/');
    }

};
