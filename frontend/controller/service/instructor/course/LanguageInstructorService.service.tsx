import { INSTRUCTOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {LanguageDto} from '@/controller/model/course/Language.model';
import {LanguageCriteria} from '@/controller/criteria/course/LanguageCriteria.model';

export class LanguageInstructorService extends AbstractService<LanguageDto, LanguageCriteria>{

    constructor() {
        super(INSTRUCTOR_URL , 'language/');
    }

};
