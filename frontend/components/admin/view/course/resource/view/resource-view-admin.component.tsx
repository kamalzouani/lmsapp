import {Column} from 'primereact/column';
import {TabPanel, TabView} from 'primereact/tabview';
import {DataTable} from 'primereact/datatable';
import {Dialog} from 'primereact/dialog';
import {InputNumber} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {InputTextarea} from 'primereact/inputtextarea';
import React from 'react';
import {Calendar} from 'primereact/calendar';
import {InputSwitch} from 'primereact/inputswitch';
import {TFunction} from "i18next";

import  {ResourceDto}  from '@/controller/model/course/Resource.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type ResourceViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: ResourceDto,
    t: TFunction
}

const View: React.FC<ResourceViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<ResourceDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("resource.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("resource.tabPan")}>
    <div className="formgrid grid">

                <div className="field col-6">
                    <label htmlFor="lesson">{t("resource.lesson")}</label>
                    <InputText  id="lessonDropdown"  value={selectedItem?.lesson?.label}  disabled  />
                </div>
            <div className="field col-6">
                <label htmlFor="type">{t("resource.type")}</label>
                <InputText id="type" value={selectedItem?.type} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="url">{t("resource.url")}</label>
                <InputText id="url" value={selectedItem?.url} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="file">{t("resource.file")}</label>
                <InputText id="file" value={selectedItem?.file} disabled   />
            </div>

        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
