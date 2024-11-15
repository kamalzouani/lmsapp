import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {LessonDto} from '@/controller/model/course/Lesson.model';
import {CurriculumDto} from '@/controller/model/course/Curriculum.model';
import {ResourceDto} from '@/controller/model/course/Resource.model';

export class ModuleDto extends BaseDto{

    public label: string;

    public description: string;

    public curriculum: null | CurriculumDto ;
     public lessons: Array<LessonDto>;


    constructor() {
        super();
        this.label = '';
        this.description = '';
        this.curriculum = null;
        this.lessons = new Array<LessonDto>();
        }

    getClassName() {
        return "Module";
    }
}
