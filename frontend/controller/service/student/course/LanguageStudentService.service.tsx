import { STUDENT_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {LanguageDto} from '@/controller/model/course/Language.model';
import {LanguageCriteria} from '@/controller/criteria/course/LanguageCriteria.model';

export class LanguageStudentService extends AbstractService<LanguageDto, LanguageCriteria>{

    constructor() {
        super(STUDENT_URL , 'language/');
    }

};
