import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {TabView, TabPanel} from 'primereact/tabview';
import {DataTable} from 'primereact/datatable';
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

import {ModuleAdminService} from '@/controller/service/admin/course/ModuleAdminService.service';
import  {ModuleDto}  from '@/controller/model/course/Module.model';
import {ModuleCriteria} from "@/controller/criteria/course/ModuleCriteria.model";

import {LessonDto} from '@/controller/model/course/Lesson.model';
import {LessonAdminService} from '@/controller/service/admin/course/LessonAdminService.service';
import {CurriculumDto} from '@/controller/model/course/Curriculum.model';
import {CurriculumAdminService} from '@/controller/service/admin/course/CurriculumAdminService.service';
import {ResourceDto} from '@/controller/model/course/Resource.model';
import {ResourceAdminService} from '@/controller/service/admin/course/ResourceAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type ModuleCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: ModuleDto) => void,
    showToast: React.Ref<Toast>,
    list: ModuleDto[],
    service: ModuleAdminService,
    t: TFunction
}
const Create: React.FC<ModuleCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
                if(item.label == '')
                    errorMessages.push("label is required")
        return errorMessages.length == 0 ;
    }
    const emptyItem = new ModuleDto();
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
        } = useCreateHook<ModuleDto, ModuleCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [curriculums, setCurriculums] = useState<CurriculumDto[]>([]);

    const [lessons, setLessons] = useState<LessonDto>(new LessonDto());

    const lessonAdminService = new LessonAdminService();
    const curriculumAdminService = new CurriculumAdminService();
    const resourceAdminService = new ResourceAdminService();
    useEffect(() => {
        curriculumAdminService.getList().then(({data}) => setCurriculums(data)).catch(error => console.log(error));



    }, []);




    const addLessons = () => {
        setSubmitted(true);
        if( item.lessons == null )
        item.lessons = new Array<LessonDto>();
        let _item = lessons;
        if (!_item.id) {
            item.lessons.push(_item);
            MessageService.showSuccess(showToast, 'Lessons Created');
            setItem((prevState :any) => ({...prevState, lessons: item.lessons }));
        } else {
            const updatedItems = item.lessons.map((item) => item.id === lessons.id ? {...lessons} : item);
            MessageService.showSuccess(showToast,'Lessons Updated');
            setItem((prevState :any) => ({ ...prevState, lessons: updatedItems}));
        }
        setLessons(new LessonDto());
    };

    const deleteLessons = (rowData: any) => {
        const updatedItems = item.lessons.filter((val) => val !== rowData);
        setItem((prevState ) => ({...prevState as any,lessons: updatedItems }));
        setLessons(new LessonDto());
        MessageService.showSuccess(showToast, 'ModuleItem Deleted');
    };

    const editLessons = (rowData: any) => {
         setActiveTab(0);
         setLessons(rowData);

    };

    const onInputNumerChangeLessons = (e: any, name: string) => {
         const val = e.value || 0;
         setLessons((prevLessons) => ({...prevLessons as any, [name]: val, }));
    };
    const onDropdownChangeLessons = (e: any, field: string) => {
        setLessons((prevState) => ({ ...prevState as any, [field]: e.value}));
    };

    const onBooleanInputChangeLessons = (e: InputSwitchChangeEvent, name: string) => {
       const val = e.value;
       setLessons((prevItem) => ({ ...prevItem as any, [name]: val, }));
    };

    const onInputDateChangeLessons = (e: any, name: string) => {
        const val = e.value || '';
        setLessons({ ...lessons as any, [name]:val})
    };

    const onInputTextChangeLessons = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, name: string) => {
        const val = (e.target && e.target.value) || '';
        setLessons({ ...lessons as any, [name]:val})
    };




    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("module.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("module.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="curriculum">{t("module.curriculum")}</label>
                        <Dropdown  id="curriculumDropdown"  value={item.curriculum} options={curriculums} onChange={(e) => onDropdownChange(e, 'curriculum')}   placeholder={t("module.curriculumPlaceHolder")} filter filterPlaceholder={t("module.curriculumPlaceHolderFilter")} optionLabel="id" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="label">{t("module.label")}</label>
                        <InputText id="label" value={item.label} onChange={(e) => onInputTextChange(e, 'label')} required className={classNames({'p-invalid': submitted && !item.label})} />
                        {submitted && !item.label && <small className="p-invalid">Label is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="description">{t("module.description")}</label>
                        <InputText id="description" value={item.description} onChange={(e) => onInputTextChange(e, 'description')} required className={classNames({'p-invalid': submitted && !item.description})} />
                        {submitted && !item.description && <small className="p-invalid">Description is required.</small>}
                    </div>
                </div>
            </TabPanel>
            <TabPanel header={t("module.lessons")}>
                        <div className="grid">
                            <div className="field col-6">
                                <label htmlFor="label">{t("lesson.label")}</label>
                                <InputText id="label" value={lessons.label} onChange={(e) => onInputTextChangeLessons(e, 'label')} required className={classNames({'p-invalid': submitted && !item.lessons})}/>
                            </div>
                            <div className="field col-6">
                                <label htmlFor="description">{t("lesson.description")}</label>
                                <InputText id="description" value={lessons.description} onChange={(e) => onInputTextChangeLessons(e, 'description')} required className={classNames({'p-invalid': submitted && !item.lessons})}/>
                            </div>
                            <div className="field col-6">
                                    <label htmlFor="content">{t("lesson.content")}</label>
                                    <span className="p-float-label">
                                    <InputTextarea id="content" value={item ? item.lessons : ''} onChange={(e) => onInputTextChange(e, 'content')} rows={5} cols={30}/>
                                </span>
                            </div>
                            <div className="field col-1">
                                <Button icon="pi pi-plus" label="OK" className="mt-4" onClick={addLessons} />
                            </div>
                        </div>
                    <div className="p-col">
                    <div className="card">
                    <DataTable value={item.lessons} tableStyle={{minWidth: '50rem'}} dataKey="id">
                        <Column field="label" header={t("lesson.label")} ></Column>
                        <Column field="description" header={t("lesson.description")} ></Column>
                        <Column field="content" header={t("lesson.content")} ></Column>
                        <Column header={t("actions")} body={(rowData)=> (<div>
                        <Button icon="pi pi-times" rounded severity="warning" className="mr-2 p-button-danger" onClick={()=> deleteLessons(rowData)} />
                        <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={()=> editLessons(rowData)} /> </div>)}></Column>
                    </DataTable>
                    </div>
                    </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
