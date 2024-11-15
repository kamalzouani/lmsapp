import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {TabView, TabPanel} from 'primereact/tabview';
import {Dialog} from 'primereact/dialog';
import {InputNumber, InputNumberChangeEvent} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {classNames} from 'primereact/utils';
import { InputTextarea } from 'primereact/inputtextarea';
import React, {useEffect, useState} from 'react';
import { Calendar } from 'primereact/calendar';
import { format } from 'date-fns';
import {InputSwitch, InputSwitchChangeEvent} from 'primereact/inputswitch';
import {Dropdown, DropdownChangeEvent} from 'primereact/dropdown';
import {MultiSelect, MultiSelectChangeEvent} from 'primereact/multiselect';
import {MessageService} from '@/utils/zynerator/service/MessageService';

import {CourseAdminService} from '@/controller/service/admin/course/CourseAdminService.service';
import  {CourseDto}  from '@/controller/model/course/Course.model';
import {CourseCriteria} from "@/controller/criteria/course/CourseCriteria.model";

import {CategoryDto} from '@/controller/model/course/Category.model';
import {CategoryAdminService} from '@/controller/service/admin/course/CategoryAdminService.service';
import {InstructorDto} from '@/controller/model/instructor/Instructor.model';
import {InstructorAdminService} from '@/controller/service/admin/instructor/InstructorAdminService.service';
import {CurriculumDto} from '@/controller/model/course/Curriculum.model';
import {CurriculumAdminService} from '@/controller/service/admin/course/CurriculumAdminService.service';
import {LanguageDto} from '@/controller/model/course/Language.model';
import {LanguageAdminService} from '@/controller/service/admin/course/LanguageAdminService.service';
import {LevelDto} from '@/controller/model/course/Level.model';
import {LevelAdminService} from '@/controller/service/admin/course/LevelAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type CourseCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: CourseDto) => void,
    showToast: React.Ref<Toast>,
    list: CourseDto[],
    service: CourseAdminService,
    t: TFunction
}
const Create: React.FC<CourseCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
                if(item.code == '')
                    errorMessages.push("code is required")
                if(item.label == '')
                    errorMessages.push("label is required")
        return errorMessages.length == 0 ;
    }
    const emptyItem = new CourseDto();
        const {
            item,
            setItem,
            submitted,
            setSubmitted,
            activeIndex,
            setActiveIndex,
            activeTab,
            setActiveTab,
            onInputTextChange,
            onInputDateChange,
            onInputNumerChange,
            onMultiSelectChange,
            onBooleanInputChange,
            onTabChange,
            onDropdownChange,
            hideDialog,
            saveItem,
            formateDate
        } = useCreateHook<CourseDto, CourseCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [levels, setLevels] = useState<LevelDto[]>([]);
    const [categorys, setCategorys] = useState<CategoryDto[]>([]);
    const [instructors, setInstructors] = useState<InstructorDto[]>([]);
    const [curriculums, setCurriculums] = useState<CurriculumDto[]>([]);
    const [languages, setLanguages] = useState<LanguageDto[]>([]);


    const categoryAdminService = new CategoryAdminService();
    const instructorAdminService = new InstructorAdminService();
    const curriculumAdminService = new CurriculumAdminService();
    const languageAdminService = new LanguageAdminService();
    const levelAdminService = new LevelAdminService();
    useEffect(() => {
        instructorAdminService.getList().then(({data}) => setInstructors(data)).catch(error => console.log(error));
        categoryAdminService.getList().then(({data}) => setCategorys(data)).catch(error => console.log(error));
        levelAdminService.getList().then(({data}) => setLevels(data)).catch(error => console.log(error));
        languageAdminService.getList().then(({data}) => setLanguages(data)).catch(error => console.log(error));
        curriculumAdminService.getList().then(({data}) => setCurriculums(data)).catch(error => console.log(error));
    }, []);








    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("course.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("course.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="code">{t("course.code")}</label>
                        <InputText id="code" value={item.code} onChange={(e) => onInputTextChange(e, 'code')} required className={classNames({'p-invalid': submitted && !item.code})} />
                        {submitted && !item.code && <small className="p-invalid">Code is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="label">{t("course.label")}</label>
                        <InputText id="label" value={item.label} onChange={(e) => onInputTextChange(e, 'label')} required className={classNames({'p-invalid': submitted && !item.label})} />
                        {submitted && !item.label && <small className="p-invalid">Label is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="description">{t("course.description")}</label>
                        <span className="p-float-label">
                        <InputTextarea id="description" value={item.description} onChange={(e) => onInputTextChange(e, 'description')} rows={5} cols={30}/>
                    </span>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="startDate">{t("course.startDate")}</label>
                        <Calendar id="startDate" value={item.startDate} onChange={(e) => onInputDateChange(e, 'startDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="endDate">{t("course.endDate")}</label>
                        <Calendar id="endDate" value={item.endDate} onChange={(e) => onInputDateChange(e, 'endDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="duration">{t("course.duration")}</label>
                        <InputNumber id="duration" value={item.duration} onChange={(e) => onInputNumerChange(e, 'duration')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="price">{t("course.price")}</label>
                        <InputNumber id="price" value={item.price} onChange={(e) => onInputNumerChange(e, 'price')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="instructor">{t("course.instructor")}</label>
                        <Dropdown  id="instructorDropdown"  value={item.instructor} options={instructors} onChange={(e) => onDropdownChange(e, 'instructor')}   placeholder={t("course.instructorPlaceHolder")} filter filterPlaceholder={t("course.instructorPlaceHolderFilter")} optionLabel="email" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="category">{t("course.category")}</label>
                        <Dropdown  id="categoryDropdown"  value={item.category} options={categorys} onChange={(e) => onDropdownChange(e, 'category')}   placeholder={t("course.categoryPlaceHolder")} filter filterPlaceholder={t("course.categoryPlaceHolderFilter")} optionLabel="label" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="level">{t("course.level")}</label>
                        <Dropdown  id="levelDropdown"  value={item.level} options={levels} onChange={(e) => onDropdownChange(e, 'level')}   placeholder={t("course.levelPlaceHolder")} filter filterPlaceholder={t("course.levelPlaceHolderFilter")} optionLabel="label" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="language">{t("course.language")}</label>
                        <Dropdown  id="languageDropdown"  value={item.language} options={languages} onChange={(e) => onDropdownChange(e, 'language')}   placeholder={t("course.languagePlaceHolder")} filter filterPlaceholder={t("course.languagePlaceHolderFilter")} optionLabel="label" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="requirements">{t("course.requirements")}</label>
                        <InputText id="requirements" value={item.requirements} onChange={(e) => onInputTextChange(e, 'requirements')} required className={classNames({'p-invalid': submitted && !item.requirements})} />
                        {submitted && !item.requirements && <small className="p-invalid">Requirements is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="learningOutcomes">{t("course.learningOutcomes")}</label>
                        <InputText id="learningOutcomes" value={item.learningOutcomes} onChange={(e) => onInputTextChange(e, 'learningOutcomes')} required className={classNames({'p-invalid': submitted && !item.learningOutcomes})} />
                        {submitted && !item.learningOutcomes && <small className="p-invalid">LearningOutcomes is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="curriculum">{t("course.curriculum")}</label>
                        <Dropdown  id="curriculumDropdown"  value={item.curriculum} options={curriculums} onChange={(e) => onDropdownChange(e, 'curriculum')}   placeholder={t("course.curriculumPlaceHolder")} filter filterPlaceholder={t("course.curriculumPlaceHolderFilter")} optionLabel="id" showClear/>
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
