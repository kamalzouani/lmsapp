import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {CategoryDto} from '@/controller/model/course/Category.model';
import {CategoryCriteria} from '@/controller/criteria/course/CategoryCriteria.model';

export class CategoryInstructorService extends AbstractService<CategoryDto, CategoryCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'category/');
    }

};
