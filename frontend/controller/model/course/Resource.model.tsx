import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {LessonDto} from '@/controller/model/course/Lesson.model';

export class ResourceDto extends BaseDto{

    public type: string;

    public url: string;

    public file: string;

    public lesson: null | LessonDto ;


    constructor() {
        super();
        this.type = '';
        this.url = '';
        this.file = '';
        this.lesson = null;
        }

    getClassName() {
        return "Resource";
    }
}
