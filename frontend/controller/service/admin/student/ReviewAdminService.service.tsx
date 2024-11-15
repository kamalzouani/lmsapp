import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ReviewDto} from '@/controller/model/student/Review.model';
import {ReviewCriteria} from '@/controller/criteria/student/ReviewCriteria.model';

export class ReviewAdminService extends AbstractService<ReviewDto, ReviewCriteria>{

    constructor() {
        super(ADMIN_URL , 'review/');
    }

};
