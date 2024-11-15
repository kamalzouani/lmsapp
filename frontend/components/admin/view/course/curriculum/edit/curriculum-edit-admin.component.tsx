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


import {CurriculumAdminService} from '@/controller/service/admin/course/CurriculumAdminService.service';
import  {CurriculumDto}  from '@/controller/model/course/Curriculum.model';
import {CurriculumCriteria} from "@/controller/criteria/course/CurriculumCriteria.model";


import {LessonDto} from '@/controller/model/course/Lesson.model';
import {LessonAdminService} from '@/controller/service/admin/course/LessonAdminService.service';
import {ModuleDto} from '@/controller/model/course/Module.model';
import {ModuleAdminService} from '@/controller/service/admin/course/ModuleAdminService.service';
import {CourseDto} from '@/controller/model/course/Course.model';
import {CourseAdminService} from '@/controller/service/admin/course/CourseAdminService.service';


type CurriculumEditAdminType = {
    visible: boolean,
    onClose: () => void,
    showToast: React.Ref<Toast>,
    selectedItem: CurriculumDto
    update: (item: CurriculumDto) => void,
    list: CurriculumDto[],
    service: CurriculumAdminService,
    t: TFunction
}
const Edit: React.FC<CurriculumEditAdminType> = ({visible, onClose, showToast, selectedItem, update, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        return errorMessages.length == 0 ;
    }
    const emptyItem = new CurriculumDto();


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
        } = useEditHook<CurriculumDto, CurriculumCriteria>({list, selectedItem, onClose, update, showToast,service, t, isFormValid})

    const [courses, setCourses] = useState<CourseDto[]>([]);

    const [modules, setModules] = useState<ModuleDto>(new ModuleDto());

    const lessonAdminService = new LessonAdminService();
    const moduleAdminService = new ModuleAdminService();
    const courseAdminService = new CourseAdminService();
    useEffect(() => {
    courseAdminService.getList().then(({data}) => setCourses(data)).catch(error => console.log(error));



        }, []);






    const addModules = () => {
        setSubmitted(true);
        if( item.modules == null )
        item.modules = new Array<ModuleDto>();
        let _item = modules;
        if (!_item.id) {
            item.modules.push(_item);
            MessageService.showSuccess(showToast, 'Modules Created');
            setItem((prevState :any) => ({...prevState, modules: item.modules }));
        } else {
            const updatedItems = item.modules.map((item) => item.id === modules.id ? {...modules} : item);
            MessageService.showSuccess(showToast, 'Modules Updated');
            setItem((prevState :any) => ({ ...prevState, modules: updatedItems}));
        }
        setModules(new ModuleDto());
    };

    const deleteModules = (rowData: any) => {
        const updatedItems = item.modules.filter((val) => val !== rowData);
        setItem((prevState ) => ({...prevState as any, modules: updatedItems }));
        setModules(new ModuleDto());
        MessageService.showSuccess(showToast, 'CurriculumItem Deleted');
    };

    const editModules = (rowData: any) => {
        setActiveTab(0);
        setModules(rowData);
    };

    const onInputNumerChangeModules = (e: any, name: string) => {
        const val = e.value || 0;
        setModules((prevModules) => ({ ...prevModules  as any, [name]: val, }));
    };

    const onDropdownChangeModules = (e: any, field: string) => {
        setModules((prevState) => ({ ...prevState  as any, [field]: e.value}));
    };


    const onMultiSelectChangeModules = (e: any, field: string) => {
        if (e && e.value && Array.isArray(e.value)) {
            const selectedValues = e.value.map((option  : any) => option && option.value);
            setModules(prevState => ({ ...prevState as any, [field]: selectedValues, }));
        }
    };

    const onBooleanInputChangeModules = (e: any, name: string) => {
        const val = e.value;
        setModules((prevItem) => ({ ...prevItem as any, [name]: val, }));
    };

    const onInputDateChangeModules = (e: any, name: string) => {
        const val = e.value || '';
        setModules({ ...modules  as any, [name]:val})
    };

    const onInputTextChangeModules = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, name: string) => {
        const val = (e.target && e.target.value) || '';
        setModules({ ...modules  as any, [name]:val})
    };

    const itemDialogFooter = ( <>
        <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
        <Button label="Save" icon="pi pi-check" onClick={editItem} /> </>
    );



    return(
    <Dialog visible={visible} style={{width: '70vw'}} header={t("curriculum.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog}>
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("curriculum.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="course">{t("curriculum.course")}</label>
                        <Dropdown  id="courseDropdown"  value={item ? item.course : ''} options={courses} onChange={(e) => onDropdownChange(e, 'course')}   placeholder="Sélectionnez un course" filter filterPlaceholder="Rechercher un course" optionLabel="label" showClear />
                    </div>
                </div>
            </TabPanel>
            <TabPanel header={t("curriculum.modules")}>
                        <div className="grid">
                            <div className="field col-6">
                                <label htmlFor="label">{t("module.label")}</label>
                                <InputText id="label" value={modules.label} onChange={(e) => onInputTextChangeModules(e, 'label')} required className={classNames({'p-invalid': submitted && !item.modules})}/>
                            </div>
                            <div className="field col-6">
                                <label htmlFor="description">{t("module.description")}</label>
                                <InputText id="description" value={modules.description} onChange={(e) => onInputTextChangeModules(e, 'description')} required className={classNames({'p-invalid': submitted && !item.modules})}/>
                            </div>

                            <div className="field col-1">
                                <Button icon="pi pi-plus" label="OK" className="mt-4" onClick={addModules} />
                            </div>
                        </div>
                        <div className="p-col">
                        <div className="card">
                            <DataTable value={item.modules} tableStyle={{minWidth: '50rem'}} dataKey="id">
                                <Column field="label" header={t("module.label")} ></Column>
                                <Column field="description" header={t("module.description")} ></Column>
                                <Column header="Actions" body={(rowData) => (
                                    <div>
                                        <Button icon="pi pi-times" rounded severity="warning" className="mr-2 p-button-danger" onClick={()=> deleteModules(rowData)} />
                                        <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={()=> editModules(rowData)} />
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


