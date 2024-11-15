import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {CurriculumDto} from '@/controller/model/course/Curriculum.model';
import {CurriculumCriteria} from '@/controller/criteria/course/CurriculumCriteria.model';

export class CurriculumInstructorService extends AbstractService<CurriculumDto, CurriculumCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'curriculum/');
    }

};
