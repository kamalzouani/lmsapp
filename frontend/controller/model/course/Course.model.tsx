import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {CategoryDto} from '@/controller/model/course/Category.model';
import {InstructorDto} from '@/controller/model/instructor/Instructor.model';
import {CurriculumDto} from '@/controller/model/course/Curriculum.model';
import {LanguageDto} from '@/controller/model/course/Language.model';
import {LevelDto} from '@/controller/model/course/Level.model';

export class CourseDto extends BaseDto{

    public code: string;

    public label: string;

    public description: string;

   public startDate: null | Date;

   public endDate: null | Date;

    public duration: null | number;

    public price: null | number;

    public requirements: string;

    public learningOutcomes: string;

    public instructor: null | InstructorDto ;
    public category: null | CategoryDto ;
    public level: null | LevelDto ;
    public language: null | LanguageDto ;
    public curriculum: null | CurriculumDto ;


    constructor() {
        super();
        this.code = '';
        this.label = '';
        this.description = '';
        this.startDate = null;
        this.endDate = null;
        this.duration = null;
        this.price = null;
        this.requirements = '';
        this.learningOutcomes = '';
        this.instructor = null;
        this.category = null;
        this.level = null;
        this.language = null;
        this.curriculum = null;
        }

    getClassName() {
        return "Course";
    }
}
