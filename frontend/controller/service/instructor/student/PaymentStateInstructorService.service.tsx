import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {PaymentStateDto} from '@/controller/model/student/PaymentState.model';
import {PaymentStateCriteria} from '@/controller/criteria/student/PaymentStateCriteria.model';

export class PaymentStateInstructorService extends AbstractService<PaymentStateDto, PaymentStateCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'paymentState/');
    }

};
