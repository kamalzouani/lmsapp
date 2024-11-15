import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {CategoryCriteria} from '@/controller/criteria/course/CategoryCriteria.model';
import {InstructorCriteria} from '@/controller/criteria/instructor/InstructorCriteria.model';
import {CurriculumCriteria} from '@/controller/criteria/course/CurriculumCriteria.model';
import {LanguageCriteria} from '@/controller/criteria/course/LanguageCriteria.model';
import {LevelCriteria} from '@/controller/criteria/course/LevelCriteria.model';

export class CourseCriteria  extends  BaseCriteria {

    public id: number | null;;

    public code: string;
    public codeLike: string;
    public label: string;
    public labelLike: string;
    public description: string;
    public descriptionLike: string;
    public startDate: null | Date;
    public startDateFrom: null | Date;
    public startDateTo: null | Date;
    public endDate: null | Date;
    public endDateFrom: null | Date;
    public endDateTo: null | Date;
     public duration: null | number;
     public durationMin: null | number;
     public durationMax: null | number;
     public price: null | number;
     public priceMin: null | number;
     public priceMax: null | number;
    public requirements: string;
    public requirementsLike: string;
    public learningOutcomes: string;
    public learningOutcomesLike: string;
    public instructor: InstructorCriteria ;
    public instructors: Array<InstructorCriteria> ;
    public category: CategoryCriteria ;
    public categorys: Array<CategoryCriteria> ;
    public level: LevelCriteria ;
    public levels: Array<LevelCriteria> ;
    public language: LanguageCriteria ;
    public languages: Array<LanguageCriteria> ;
    public curriculum: CurriculumCriteria ;
    public curriculums: Array<CurriculumCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.code = '';
        this.codeLike = '';
        this.label = '';
        this.labelLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.startDate = null;
        this.startDateFrom  = null;
        this.startDateTo = null;
        this.endDate = null;
        this.endDateFrom  = null;
        this.endDateTo = null;
        this.duration = null;
        this.durationMin = null;
        this.durationMax = null;
        this.price = null;
        this.priceMin = null;
        this.priceMax = null;
        this.requirements = '';
        this.requirementsLike = '';
        this.learningOutcomes = '';
        this.learningOutcomesLike = '';
        this.instructor = new InstructorCriteria();
        this.instructors = new Array<InstructorCriteria>() ;
        this.category = new CategoryCriteria();
        this.categorys = new Array<CategoryCriteria>() ;
        this.level = new LevelCriteria();
        this.levels = new Array<LevelCriteria>() ;
        this.language = new LanguageCriteria();
        this.languages = new Array<LanguageCriteria>() ;
        this.curriculum = new CurriculumCriteria();
        this.curriculums = new Array<CurriculumCriteria>() ;
    }

}
