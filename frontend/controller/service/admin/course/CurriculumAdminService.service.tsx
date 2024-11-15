import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {CurriculumDto} from '@/controller/model/course/Curriculum.model';
import {CurriculumCriteria} from '@/controller/criteria/course/CurriculumCriteria.model';

export class CurriculumAdminService extends AbstractService<CurriculumDto, CurriculumCriteria>{

    constructor() {
        super(ADMIN_URL , 'curriculum/');
    }

};
