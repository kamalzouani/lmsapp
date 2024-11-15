import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {PaymentDto} from '@/controller/model/student/Payment.model';
import {PaymentCriteria} from '@/controller/criteria/student/PaymentCriteria.model';

export class PaymentInstructorService extends AbstractService<PaymentDto, PaymentCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'payment/');
    }

};
