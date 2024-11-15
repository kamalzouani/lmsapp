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

import {CategoryAdminService} from '@/controller/service/admin/course/CategoryAdminService.service';
import  {CategoryDto}  from '@/controller/model/course/Category.model';
import {CategoryCriteria} from "@/controller/criteria/course/CategoryCriteria.model";

import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type CategoryCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: CategoryDto) => void,
    showToast: React.Ref<Toast>,
    list: CategoryDto[],
    service: CategoryAdminService,
    t: TFunction
}
const Create: React.FC<CategoryCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
                if(item.code == '')
                    errorMessages.push("code is required")
                if(item.label == '')
                    errorMessages.push("label is required")
                if(item.style == '')
                    errorMessages.push("style is required")
        return errorMessages.length == 0 ;
    }
    const emptyItem = new CategoryDto();
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
        } = useCreateHook<CategoryDto, CategoryCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})


    useEffect(() => {
    }, []);








    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("category.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("category.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="code">{t("category.code")}</label>
                        <InputText id="code" value={item.code} onChange={(e) => onInputTextChange(e, 'code')} required className={classNames({'p-invalid': submitted && !item.code})} />
                        {submitted && !item.code && <small className="p-invalid">Code is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="label">{t("category.label")}</label>
                        <InputText id="label" value={item.label} onChange={(e) => onInputTextChange(e, 'label')} required className={classNames({'p-invalid': submitted && !item.label})} />
                        {submitted && !item.label && <small className="p-invalid">Label is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="style">{t("category.style")}</label>
                        <InputText id="style" value={item.style} onChange={(e) => onInputTextChange(e, 'style')} required className={classNames({'p-invalid': submitted && !item.style})} />
                        {submitted && !item.style && <small className="p-invalid">Style is required.</small>}
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
