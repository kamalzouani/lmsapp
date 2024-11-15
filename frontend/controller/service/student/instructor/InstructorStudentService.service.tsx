import { STUDENT_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {InstructorDto} from '@/controller/model/instructor/Instructor.model';
import {InstructorCriteria} from '@/controller/criteria/instructor/InstructorCriteria.model';

export class InstructorStudentService extends AbstractService<InstructorDto, InstructorCriteria>{

    constructor() {
        super(STUDENT_URL , 'instructor/');
    }

};
