import { STUDENT_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {StudentDto} from '@/controller/model/student/Student.model';
import {StudentCriteria} from '@/controller/criteria/student/StudentCriteria.model';

export class StudentStudentService extends AbstractService<StudentDto, StudentCriteria>{

    constructor() {
        super(STUDENT_URL , 'student/');
    }

};