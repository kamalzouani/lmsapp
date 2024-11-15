import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {EnrollmentDto} from '@/controller/model/student/Enrollment.model';
import {EnrollmentCriteria} from '@/controller/criteria/student/EnrollmentCriteria.model';

export class EnrollmentInstructorService extends AbstractService<EnrollmentDto, EnrollmentCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'enrollment/');
    }

};
