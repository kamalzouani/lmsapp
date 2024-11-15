import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {EnrollmentStateDto} from '@/controller/model/student/EnrollmentState.model';
import {EnrollmentStateCriteria} from '@/controller/criteria/student/EnrollmentStateCriteria.model';

export class EnrollmentStateInstructorService extends AbstractService<EnrollmentStateDto, EnrollmentStateCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'enrollmentState/');
    }

};
