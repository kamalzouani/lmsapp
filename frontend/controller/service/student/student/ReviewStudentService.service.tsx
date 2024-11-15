import { STUDENT_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ReviewDto} from '@/controller/model/student/Review.model';
import {ReviewCriteria} from '@/controller/criteria/student/ReviewCriteria.model';

export class ReviewStudentService extends AbstractService<ReviewDto, ReviewCriteria>{

    constructor() {
        super(STUDENT_URL , 'review/');
    }

};
