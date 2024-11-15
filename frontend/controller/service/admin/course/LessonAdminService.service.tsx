import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {LessonDto} from '@/controller/model/course/Lesson.model';
import {LessonCriteria} from '@/controller/criteria/course/LessonCriteria.model';

export class LessonAdminService extends AbstractService<LessonDto, LessonCriteria>{

    constructor() {
        super(ADMIN_URL , 'lesson/');
    }

};
