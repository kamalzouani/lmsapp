import { STUDENT_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {CategoryDto} from '@/controller/model/course/Category.model';
import {CategoryCriteria} from '@/controller/criteria/course/CategoryCriteria.model';

export class CategoryStudentService extends AbstractService<CategoryDto, CategoryCriteria>{

    constructor() {
        super(STUDENT_URL , 'category/');
    }

};
