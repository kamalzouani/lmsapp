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


import {LanguageAdminService} from '@/controller/service/admin/course/LanguageAdminService.service';
import  {LanguageDto}  from '@/controller/model/course/Language.model';
import {LanguageCriteria} from "@/controller/criteria/course/LanguageCriteria.model";




type LanguageEditAdminType = {
    visible: boolean,
    onClose: () => void,
    showToast: React.Ref<Toast>,
    selectedItem: LanguageDto
    update: (item: LanguageDto) => void,
    list: LanguageDto[],
    service: LanguageAdminService,
    t: TFunction
}
const Edit: React.FC<LanguageEditAdminType> = ({visible, onClose, showToast, selectedItem, update, list, service, t}) => {


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
    const emptyItem = new LanguageDto();


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
        } = useEditHook<LanguageDto, LanguageCriteria>({list, selectedItem, onClose, update, showToast,service, t, isFormValid})



    useEffect(() => {


        }, []);







    const itemDialogFooter = ( <>
        <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
        <Button label="Save" icon="pi pi-check" onClick={editItem} /> </>
    );



    return(
    <Dialog visible={visible} style={{width: '70vw'}} header={t("language.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog}>
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("language.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="code">{t("language.code")}</label>
                        <InputText id="code" value={item ? item.code : ''} onChange={(e) => onInputTextChange(e, 'code')} required className={classNames({'p-invalid': submitted && !item.code})} />
                        {submitted && !item.code && <small className="p-invalid">Code is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="label">{t("language.label")}</label>
                        <InputText id="label" value={item ? item.label : ''} onChange={(e) => onInputTextChange(e, 'label')} required className={classNames({'p-invalid': submitted && !item.label})} />
                        {submitted && !item.label && <small className="p-invalid">Label is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="style">{t("language.style")}</label>
                        <InputText id="style" value={item ? item.style : ''} onChange={(e) => onInputTextChange(e, 'style')} required className={classNames({'p-invalid': submitted && !item.style})} />
                        {submitted && !item.style && <small className="p-invalid">Style is required.</small>}
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Edit;


