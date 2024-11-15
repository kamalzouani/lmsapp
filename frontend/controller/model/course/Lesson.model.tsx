import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {ModuleDto} from '@/controller/model/course/Module.model';
import {ResourceDto} from '@/controller/model/course/Resource.model';

export class LessonDto extends BaseDto{

    public label: string;

    public description: string;

    public content: string;

    public module: null | ModuleDto ;
     public resources: Array<ResourceDto>;


    constructor() {
        super();
        this.label = '';
        this.description = '';
        this.content = '';
        this.module = null;
        this.resources = new Array<ResourceDto>();
        }

    getClassName() {
        return "Lesson";
    }
}
