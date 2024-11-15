import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ModuleDto} from '@/controller/model/course/Module.model';
import {ModuleCriteria} from '@/controller/criteria/course/ModuleCriteria.model';

export class ModuleAdminService extends AbstractService<ModuleDto, ModuleCriteria>{

    constructor() {
        super(ADMIN_URL , 'module/');
    }

};
