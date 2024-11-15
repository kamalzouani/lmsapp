import { STUDENT_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ModuleDto} from '@/controller/model/course/Module.model';
import {ModuleCriteria} from '@/controller/criteria/course/ModuleCriteria.model';

export class ModuleStudentService extends AbstractService<ModuleDto, ModuleCriteria>{

    constructor() {
        super(STUDENT_URL , 'module/');
    }

};
