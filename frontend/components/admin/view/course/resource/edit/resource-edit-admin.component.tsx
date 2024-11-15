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


import {ResourceAdminService} from '@/controller/service/admin/course/ResourceAdminService.service';
import  {ResourceDto}  from '@/controller/model/course/Resource.model';
import {ResourceCriteria} from "@/controller/criteria/course/ResourceCriteria.model";


import {LessonDto} from '@/controller/model/course/Lesson.model';
import {LessonAdminService} from '@/controller/service/admin/course/LessonAdminService.service';


type ResourceEditAdminType = {
    visible: boolean,
    onClose: () => void,
    showToast: React.Ref<Toast>,
    selectedItem: ResourceDto
    update: (item: ResourceDto) => void,
    list: ResourceDto[],
    service: ResourceAdminService,
    t: TFunction
}
const Edit: React.FC<ResourceEditAdminType> = ({visible, onClose, showToast, selectedItem, update, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        return errorMessages.length == 0 ;
    }
    const emptyItem = new ResourceDto();


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
        } = useEditHook<ResourceDto, ResourceCriteria>({list, selectedItem, onClose, update, showToast,service, t, isFormValid})

    const [lessons, setLessons] = useState<LessonDto[]>([]);


    const lessonAdminService = new LessonAdminService();
    useEffect(() => {
    lessonAdminService.getList().then(({data}) => setLessons(data)).catch(error => console.log(error));


        }, []);







    const itemDialogFooter = ( <>
        <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
        <Button label="Save" icon="pi pi-check" onClick={editItem} /> </>
    );



    return(
    <Dialog visible={visible} style={{width: '70vw'}} header={t("resource.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog}>
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("resource.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="lesson">{t("resource.lesson")}</label>
                        <Dropdown  id="lessonDropdown"  value={item ? item.lesson : ''} options={lessons} onChange={(e) => onDropdownChange(e, 'lesson')}   placeholder="Sélectionnez un lesson" filter filterPlaceholder="Rechercher un lesson" optionLabel="label" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="type">{t("resource.type")}</label>
                        <InputText id="type" value={item ? item.type : ''} onChange={(e) => onInputTextChange(e, 'type')} required className={classNames({'p-invalid': submitted && !item.type})} />
                        {submitted && !item.type && <small className="p-invalid">Type is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="url">{t("resource.url")}</label>
                        <InputText id="url" value={item ? item.url : ''} onChange={(e) => onInputTextChange(e, 'url')} required className={classNames({'p-invalid': submitted && !item.url})} />
                        {submitted && !item.url && <small className="p-invalid">Url is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="file">{t("resource.file")}</label>
                        <InputText id="file" value={item ? item.file : ''} onChange={(e) => onInputTextChange(e, 'file')} required className={classNames({'p-invalid': submitted && !item.file})} />
                        {submitted && !item.file && <small className="p-invalid">File is required.</small>}
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Edit;


