import { STUDENT_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {EnrollmentDto} from '@/controller/model/student/Enrollment.model';
import {EnrollmentCriteria} from '@/controller/criteria/student/EnrollmentCriteria.model';

export class EnrollmentStudentService extends AbstractService<EnrollmentDto, EnrollmentCriteria>{

    constructor() {
        super(STUDENT_URL , 'enrollment/');
    }

};
