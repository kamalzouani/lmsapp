import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {EnrollmentStateDto} from '@/controller/model/student/EnrollmentState.model';
import {EnrollmentStateCriteria} from '@/controller/criteria/student/EnrollmentStateCriteria.model';

export class EnrollmentStateAdminService extends AbstractService<EnrollmentStateDto, EnrollmentStateCriteria>{

    constructor() {
        super(ADMIN_URL , 'enrollmentState/');
    }

};
