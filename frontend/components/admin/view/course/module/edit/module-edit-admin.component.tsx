import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {Dropdown, DropdownChangeEvent} from 'primereact/dropdown';
import {TabView, TabPanel} from 'primereact/tabview';
import {DataTable} from 'primereact/datatable';
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


import {ModuleAdminService} from '@/controller/service/admin/course/ModuleAdminService.service';
import  {ModuleDto}  from '@/controller/model/course/Module.model';
import {ModuleCriteria} from "@/controller/criteria/course/ModuleCriteria.model";


import {LessonDto} from '@/controller/model/course/Lesson.model';
import {LessonAdminService} from '@/controller/service/admin/course/LessonAdminService.service';
import {CurriculumDto} from '@/controller/model/course/Curriculum.model';
import {CurriculumAdminService} from '@/controller/service/admin/course/CurriculumAdminService.service';
import {ResourceDto} from '@/controller/model/course/Resource.model';
import {ResourceAdminService} from '@/controller/service/admin/course/ResourceAdminService.service';


type ModuleEditAdminType = {
    visible: boolean,
    onClose: () => void,
    showToast: React.Ref<Toast>,
    selectedItem: ModuleDto
    update: (item: ModuleDto) => void,
    list: ModuleDto[],
    service: ModuleAdminService,
    t: TFunction
}
const Edit: React.FC<ModuleEditAdminType> = ({visible, onClose, showToast, selectedItem, update, list, service, t}) => {


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
        onDropdownChange,
        onTabChange,
        hideDialog,
        editItem,
        formateDate,
        parseToIsoFormat,
        adaptDate
        } = useEditHook<ModuleDto, ModuleCriteria>({list, selectedItem, onClose, update, showToast,service, t, isFormValid})

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
            MessageService.showSuccess(showToast, 'Lessons Updated');
            setItem((prevState :any) => ({ ...prevState, lessons: updatedItems}));
        }
        setLessons(new LessonDto());
    };

    const deleteLessons = (rowData: any) => {
        const updatedItems = item.lessons.filter((val) => val !== rowData);
        setItem((prevState ) => ({...prevState as any, lessons: updatedItems }));
        setLessons(new LessonDto());
        MessageService.showSuccess(showToast, 'ModuleItem Deleted');
    };

    const editLessons = (rowData: any) => {
        setActiveTab(0);
        setLessons(rowData);
    };

    const onInputNumerChangeLessons = (e: any, name: string) => {
        const val = e.value || 0;
        setLessons((prevLessons) => ({ ...prevLessons  as any, [name]: val, }));
    };

    const onDropdownChangeLessons = (e: any, field: string) => {
        setLessons((prevState) => ({ ...prevState  as any, [field]: e.value}));
    };


    const onMultiSelectChangeLessons = (e: any, field: string) => {
        if (e && e.value && Array.isArray(e.value)) {
            const selectedValues = e.value.map((option  : any) => option && option.value);
            setLessons(prevState => ({ ...prevState as any, [field]: selectedValues, }));
        }
    };

    const onBooleanInputChangeLessons = (e: any, name: string) => {
        const val = e.value;
        setLessons((prevItem) => ({ ...prevItem as any, [name]: val, }));
    };

    const onInputDateChangeLessons = (e: any, name: string) => {
        const val = e.value || '';
        setLessons({ ...lessons  as any, [name]:val})
    };

    const onInputTextChangeLessons = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, name: string) => {
        const val = (e.target && e.target.value) || '';
        setLessons({ ...lessons  as any, [name]:val})
    };

    const itemDialogFooter = ( <>
        <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
        <Button label="Save" icon="pi pi-check" onClick={editItem} /> </>
    );



    return(
    <Dialog visible={visible} style={{width: '70vw'}} header={t("module.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog}>
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("module.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="curriculum">{t("module.curriculum")}</label>
                        <Dropdown  id="curriculumDropdown"  value={item ? item.curriculum : ''} options={curriculums} onChange={(e) => onDropdownChange(e, 'curriculum')}   placeholder="Sélectionnez un curriculum" filter filterPlaceholder="Rechercher un curriculum" optionLabel="id" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="label">{t("module.label")}</label>
                        <InputText id="label" value={item ? item.label : ''} onChange={(e) => onInputTextChange(e, 'label')} required className={classNames({'p-invalid': submitted && !item.label})} />
                        {submitted && !item.label && <small className="p-invalid">Label is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="description">{t("module.description")}</label>
                        <InputText id="description" value={item ? item.description : ''} onChange={(e) => onInputTextChange(e, 'description')} required className={classNames({'p-invalid': submitted && !item.description})} />
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
                                <Column header="Actions" body={(rowData) => (
                                    <div>
                                        <Button icon="pi pi-times" rounded severity="warning" className="mr-2 p-button-danger" onClick={()=> deleteLessons(rowData)} />
                                        <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={()=> editLessons(rowData)} />
                                    </div>
                                )}></Column>
                            </DataTable>
                        </div>
                        </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Edit;


