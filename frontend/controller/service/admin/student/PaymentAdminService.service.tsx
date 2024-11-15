import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {PaymentDto} from '@/controller/model/student/Payment.model';
import {PaymentCriteria} from '@/controller/criteria/student/PaymentCriteria.model';

export class PaymentAdminService extends AbstractService<PaymentDto, PaymentCriteria>{

    constructor() {
        super(ADMIN_URL , 'payment/');
    }

};
