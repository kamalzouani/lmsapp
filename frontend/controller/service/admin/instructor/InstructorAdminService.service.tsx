import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {InstructorDto} from '@/controller/model/instructor/Instructor.model';
import {InstructorCriteria} from '@/controller/criteria/instructor/InstructorCriteria.model';

export class InstructorAdminService extends AbstractService<InstructorDto, InstructorCriteria>{

    constructor() {
        super(ADMIN_URL , 'instructor/');
    }

};
