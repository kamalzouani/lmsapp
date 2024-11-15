import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {CategoryDto} from '@/controller/model/course/Category.model';
import {CategoryCriteria} from '@/controller/criteria/course/CategoryCriteria.model';

export class CategoryAdminService extends AbstractService<CategoryDto, CategoryCriteria>{

    constructor() {
        super(ADMIN_URL , 'category/');
    }

};
