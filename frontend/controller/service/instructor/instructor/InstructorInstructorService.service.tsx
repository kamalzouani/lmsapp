import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {InstructorDto} from '@/controller/model/instructor/Instructor.model';
import {InstructorCriteria} from '@/controller/criteria/instructor/InstructorCriteria.model';

export class InstructorInstructorService extends AbstractService<InstructorDto, InstructorCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'instructor/');
    }

};
