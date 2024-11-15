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

import {LessonAdminService} from '@/controller/service/admin/course/LessonAdminService.service';
import  {LessonDto}  from '@/controller/model/course/Lesson.model';
import {LessonCriteria} from "@/controller/criteria/course/LessonCriteria.model";

import {ModuleDto} from '@/controller/model/course/Module.model';
import {ModuleAdminService} from '@/controller/service/admin/course/ModuleAdminService.service';
import {ResourceDto} from '@/controller/model/course/Resource.model';
import {ResourceAdminService} from '@/controller/service/admin/course/ResourceAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type LessonCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: LessonDto) => void,
    showToast: React.Ref<Toast>,
    list: LessonDto[],
    service: LessonAdminService,
    t: TFunction
}
const Create: React.FC<LessonCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
                if(item.label == '')
                    errorMessages.push("label is required")
        return errorMessages.length == 0 ;
    }
    const emptyItem = new LessonDto();
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
        } = useCreateHook<LessonDto, LessonCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [modules, setModules] = useState<ModuleDto[]>([]);

    const [resources, setResources] = useState<ResourceDto>(new ResourceDto());

    const moduleAdminService = new ModuleAdminService();
    const resourceAdminService = new ResourceAdminService();
    useEffect(() => {
        moduleAdminService.getList().then(({data}) => setModules(data)).catch(error => console.log(error));



    }, []);




    const addResources = () => {
        setSubmitted(true);
        if( item.resources == null )
        item.resources = new Array<ResourceDto>();
        let _item = resources;
        if (!_item.id) {
            item.resources.push(_item);
            MessageService.showSuccess(showToast, 'Resources Created');
            setItem((prevState :any) => ({...prevState, resources: item.resources }));
        } else {
            const updatedItems = item.resources.map((item) => item.id === resources.id ? {...resources} : item);
            MessageService.showSuccess(showToast,'Resources Updated');
            setItem((prevState :any) => ({ ...prevState, resources: updatedItems}));
        }
        setResources(new ResourceDto());
    };

    const deleteResources = (rowData: any) => {
        const updatedItems = item.resources.filter((val) => val !== rowData);
        setItem((prevState ) => ({...prevState as any,resources: updatedItems }));
        setResources(new ResourceDto());
        MessageService.showSuccess(showToast, 'LessonItem Deleted');
    };

    const editResources = (rowData: any) => {
         setActiveTab(0);
         setResources(rowData);

    };

    const onInputNumerChangeResources = (e: any, name: string) => {
         const val = e.value || 0;
         setResources((prevResources) => ({...prevResources as any, [name]: val, }));
    };
    const onDropdownChangeResources = (e: any, field: string) => {
        setResources((prevState) => ({ ...prevState as any, [field]: e.value}));
    };

    const onBooleanInputChangeResources = (e: InputSwitchChangeEvent, name: string) => {
       const val = e.value;
       setResources((prevItem) => ({ ...prevItem as any, [name]: val, }));
    };

    const onInputDateChangeResources = (e: any, name: string) => {
        const val = e.value || '';
        setResources({ ...resources as any, [name]:val})
    };

    const onInputTextChangeResources = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, name: string) => {
        const val = (e.target && e.target.value) || '';
        setResources({ ...resources as any, [name]:val})
    };




    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("lesson.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("lesson.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="module">{t("lesson.module")}</label>
                        <Dropdown  id="moduleDropdown"  value={item.module} options={modules} onChange={(e) => onDropdownChange(e, 'module')}   placeholder={t("lesson.modulePlaceHolder")} filter filterPlaceholder={t("lesson.modulePlaceHolderFilter")} optionLabel="label" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="label">{t("lesson.label")}</label>
                        <InputText id="label" value={item.label} onChange={(e) => onInputTextChange(e, 'label')} required className={classNames({'p-invalid': submitted && !item.label})} />
                        {submitted && !item.label && <small className="p-invalid">Label is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="description">{t("lesson.description")}</label>
                        <InputText id="description" value={item.description} onChange={(e) => onInputTextChange(e, 'description')} required className={classNames({'p-invalid': submitted && !item.description})} />
                        {submitted && !item.description && <small className="p-invalid">Description is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="content">{t("lesson.content")}</label>
                        <span className="p-float-label">
                        <InputTextarea id="content" value={item.content} onChange={(e) => onInputTextChange(e, 'content')} rows={5} cols={30}/>
                    </span>
                    </div>
                </div>
            </TabPanel>
            <TabPanel header={t("lesson.resources")}>
                        <div className="grid">
                            <div className="field col-6">
                                <label htmlFor="type">{t("resource.type")}</label>
                                <InputText id="type" value={resources.type} onChange={(e) => onInputTextChangeResources(e, 'type')} required className={classNames({'p-invalid': submitted && !item.resources})}/>
                            </div>
                            <div className="field col-6">
                                <label htmlFor="url">{t("resource.url")}</label>
                                <InputText id="url" value={resources.url} onChange={(e) => onInputTextChangeResources(e, 'url')} required className={classNames({'p-invalid': submitted && !item.resources})}/>
                            </div>
                            <div className="field col-6">
                                <label htmlFor="file">{t("resource.file")}</label>
                                <InputText id="file" value={resources.file} onChange={(e) => onInputTextChangeResources(e, 'file')} required className={classNames({'p-invalid': submitted && !item.resources})}/>
                            </div>
                            <div className="field col-1">
                                <Button icon="pi pi-plus" label="OK" className="mt-4" onClick={addResources} />
                            </div>
                        </div>
                    <div className="p-col">
                    <div className="card">
                    <DataTable value={item.resources} tableStyle={{minWidth: '50rem'}} dataKey="id">
                        <Column field="type" header={t("resource.type")} ></Column>
                        <Column field="url" header={t("resource.url")} ></Column>
                        <Column field="file" header={t("resource.file")} ></Column>
                        <Column header={t("actions")} body={(rowData)=> (<div>
                        <Button icon="pi pi-times" rounded severity="warning" className="mr-2 p-button-danger" onClick={()=> deleteResources(rowData)} />
                        <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={()=> editResources(rowData)} /> </div>)}></Column>
                    </DataTable>
                    </div>
                    </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
