import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {LessonDto} from '@/controller/model/course/Lesson.model';
import {LessonCriteria} from '@/controller/criteria/course/LessonCriteria.model';

export class LessonInstructorService extends AbstractService<LessonDto, LessonCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'lesson/');
    }

};
