import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {CourseDto} from '@/controller/model/course/Course.model';
import {CourseCriteria} from '@/controller/criteria/course/CourseCriteria.model';

export class CourseAdminService extends AbstractService<CourseDto, CourseCriteria>{

    constructor() {
        super(ADMIN_URL , 'course/');
    }

};
