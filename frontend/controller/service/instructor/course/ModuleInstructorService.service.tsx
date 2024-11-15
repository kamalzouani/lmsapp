import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ModuleDto} from '@/controller/model/course/Module.model';
import {ModuleCriteria} from '@/controller/criteria/course/ModuleCriteria.model';

export class ModuleInstructorService extends AbstractService<ModuleDto, ModuleCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'module/');
    }

};
