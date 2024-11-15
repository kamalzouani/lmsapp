import { STUDENT_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {LevelDto} from '@/controller/model/course/Level.model';
import {LevelCriteria} from '@/controller/criteria/course/LevelCriteria.model';

export class LevelStudentService extends AbstractService<LevelDto, LevelCriteria>{

    constructor() {
        super(STUDENT_URL , 'level/');
    }

};
