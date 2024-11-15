import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {LanguageDto} from '@/controller/model/course/Language.model';
import {LanguageCriteria} from '@/controller/criteria/course/LanguageCriteria.model';

export class LanguageAdminService extends AbstractService<LanguageDto, LanguageCriteria>{

    constructor() {
        super(ADMIN_URL , 'language/');
    }

};
