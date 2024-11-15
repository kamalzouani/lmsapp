import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {LessonDto} from '@/controller/model/course/Lesson.model';
import {ModuleDto} from '@/controller/model/course/Module.model';
import {CourseDto} from '@/controller/model/course/Course.model';

export class CurriculumDto extends BaseDto{

    public course: null | CourseDto ;
     public modules: Array<ModuleDto>;


    constructor() {
        super();
        this.course = null;
        this.modules = new Array<ModuleDto>();
        }

    getClassName() {
        return "Curriculum";
    }
}
