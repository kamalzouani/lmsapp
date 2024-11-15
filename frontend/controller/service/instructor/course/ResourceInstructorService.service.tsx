import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ResourceDto} from '@/controller/model/course/Resource.model';
import {ResourceCriteria} from '@/controller/criteria/course/ResourceCriteria.model';

export class ResourceInstructorService extends AbstractService<ResourceDto, ResourceCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'resource/');
    }

};
