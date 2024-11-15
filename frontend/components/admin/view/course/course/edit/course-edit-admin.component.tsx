import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {Dropdown, DropdownChangeEvent} from 'primereact/dropdown';
import {TabView, TabPanel} from 'primereact/tabview';
import {Dialog} from 'primereact/dialog';
import {InputNumber, InputNumberChangeEvent} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {classNames} from 'primereact/utils';
import { InputTextarea } from 'primereact/inputtextarea';
import React, {useEffect, useState} from 'react';
import {Calendar} from 'primereact/calendar';
import { format } from 'date-fns';
import { parse } from 'date-fns';
import { InputSwitch } from 'primereact/inputswitch';
import {MultiSelect, MultiSelectChangeEvent} from 'primereact/multiselect';


import {MessageService} from '@/utils/zynerator/service/MessageService';


import {TFunction} from "i18next";
import {Toast} from "primereact/toast";

import useEditHook from "@/utils/zyhook/useEdit.hook";


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


type CourseEditAdminType = {
    visible: boolean,
    onClose: () => void,
    showToast: React.Ref<Toast>,
    selectedItem: CourseDto
    update: (item: CourseDto) => void,
    list: CourseDto[],
    service: CourseAdminService,
    t: TFunction
}
const Edit: React.FC<CourseEditAdminType> = ({visible, onClose, showToast, selectedItem, update, list, service, t}) => {


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
        onDropdownChange,
        onTabChange,
        hideDialog,
        editItem,
        formateDate,
        parseToIsoFormat,
        adaptDate
        } = useEditHook<CourseDto, CourseCriteria>({list, selectedItem, onClose, update, showToast,service, t, isFormValid})

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
        <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
        <Button label="Save" icon="pi pi-check" onClick={editItem} /> </>
    );



    return(
    <Dialog visible={visible} style={{width: '70vw'}} header={t("course.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog}>
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("course.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="code">{t("course.code")}</label>
                        <InputText id="code" value={item ? item.code : ''} onChange={(e) => onInputTextChange(e, 'code')} required className={classNames({'p-invalid': submitted && !item.code})} />
                        {submitted && !item.code && <small className="p-invalid">Code is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="label">{t("course.label")}</label>
                        <InputText id="label" value={item ? item.label : ''} onChange={(e) => onInputTextChange(e, 'label')} required className={classNames({'p-invalid': submitted && !item.label})} />
                        {submitted && !item.label && <small className="p-invalid">Label is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="description">{t("course.description")}</label>
                        <span className="p-float-label">
                            <InputTextarea id="description" value={item ? item.description : ''} onChange={(e) => onInputTextChange(e, 'description')} rows={5} cols={30}/>
                        </span>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="startDate">{t("course.startDate")}</label>
                        <Calendar id="startDate" value={adaptDate(item?.startDate)} onChange={(e) => onInputDateChange(e, 'startDate')} dateFormat="dd/mm/yy" showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="endDate">{t("course.endDate")}</label>
                        <Calendar id="endDate" value={adaptDate(item?.endDate)} onChange={(e) => onInputDateChange(e, 'endDate')} dateFormat="dd/mm/yy" showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="duration">{t("course.duration")}</label>
                        <InputNumber id="duration" value={item ? item.duration : 0} onChange={(e) => onInputNumerChange(e, 'duration')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="price">{t("course.price")}</label>
                        <InputNumber id="price" value={item ? item.price : 0} onChange={(e) => onInputNumerChange(e, 'price')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="instructor">{t("course.instructor")}</label>
                        <Dropdown  id="instructorDropdown"  value={item ? item.instructor : ''} options={instructors} onChange={(e) => onDropdownChange(e, 'instructor')}   placeholder="Sélectionnez un instructor" filter filterPlaceholder="Rechercher un instructor" optionLabel="email" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="category">{t("course.category")}</label>
                        <Dropdown  id="categoryDropdown"  value={item ? item.category : ''} options={categorys} onChange={(e) => onDropdownChange(e, 'category')}   placeholder="Sélectionnez un category" filter filterPlaceholder="Rechercher un category" optionLabel="label" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="level">{t("course.level")}</label>
                        <Dropdown  id="levelDropdown"  value={item ? item.level : ''} options={levels} onChange={(e) => onDropdownChange(e, 'level')}   placeholder="Sélectionnez un level" filter filterPlaceholder="Rechercher un level" optionLabel="label" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="language">{t("course.language")}</label>
                        <Dropdown  id="languageDropdown"  value={item ? item.language : ''} options={languages} onChange={(e) => onDropdownChange(e, 'language')}   placeholder="Sélectionnez un language" filter filterPlaceholder="Rechercher un language" optionLabel="label" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="requirements">{t("course.requirements")}</label>
                        <InputText id="requirements" value={item ? item.requirements : ''} onChange={(e) => onInputTextChange(e, 'requirements')} required className={classNames({'p-invalid': submitted && !item.requirements})} />
                        {submitted && !item.requirements && <small className="p-invalid">Requirements is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="learningOutcomes">{t("course.learningOutcomes")}</label>
                        <InputText id="learningOutcomes" value={item ? item.learningOutcomes : ''} onChange={(e) => onInputTextChange(e, 'learningOutcomes')} required className={classNames({'p-invalid': submitted && !item.learningOutcomes})} />
                        {submitted && !item.learningOutcomes && <small className="p-invalid">LearningOutcomes is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="curriculum">{t("course.curriculum")}</label>
                        <Dropdown  id="curriculumDropdown"  value={item ? item.curriculum : ''} options={curriculums} onChange={(e) => onDropdownChange(e, 'curriculum')}   placeholder="Sélectionnez un curriculum" filter filterPlaceholder="Rechercher un curriculum" optionLabel="id" showClear />
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Edit;


