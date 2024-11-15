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

import  {ModuleDto}  from '@/controller/model/course/Module.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type ModuleViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: ModuleDto,
    t: TFunction
}

const View: React.FC<ModuleViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<ModuleDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("module.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("module.tabPan")}>
    <div className="formgrid grid">

                <div className="field col-6">
                    <label htmlFor="curriculum">{t("module.curriculum")}</label>
                    <InputText  id="curriculumDropdown"  value={selectedItem?.curriculum?.id}  disabled  />
                </div>
            <div className="field col-6">
                <label htmlFor="label">{t("module.label")}</label>
                <InputText id="label" value={selectedItem?.label} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="description">{t("module.description")}</label>
                <InputText id="description" value={selectedItem?.description} disabled   />
            </div>

        </div>
</TabPanel>
    <TabPanel header={t("module.lessons")}>
                <div className="card">
                    <DataTable value={selectedItem?.lessons} tableStyle={{minWidth: '50rem'}} dataKey="id">
                                <Column field="label" header={t("lesson.label")}   ></Column>
                                <Column field="description" header={t("lesson.description")}   ></Column>
                                <Column field="content" header={t("lesson.content")}   ></Column>
                    </DataTable>
                </div>
        </TabPanel>
</TabView>
</Dialog>
);
};
export default View;
