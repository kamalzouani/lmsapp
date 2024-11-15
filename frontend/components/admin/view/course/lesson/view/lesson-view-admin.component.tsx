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

import  {LessonDto}  from '@/controller/model/course/Lesson.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type LessonViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: LessonDto,
    t: TFunction
}

const View: React.FC<LessonViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<LessonDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("lesson.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("lesson.tabPan")}>
    <div className="formgrid grid">

                <div className="field col-6">
                    <label htmlFor="module">{t("lesson.module")}</label>
                    <InputText  id="moduleDropdown"  value={selectedItem?.module?.label}  disabled  />
                </div>
            <div className="field col-6">
                <label htmlFor="label">{t("lesson.label")}</label>
                <InputText id="label" value={selectedItem?.label} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="description">{t("lesson.description")}</label>
                <InputText id="description" value={selectedItem?.description} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="content">{t("lesson.content")}</label>
                <span className="p-float-label">
                   <InputTextarea id="content" value={selectedItem?.content} disabled rows={5} cols={30} />
                </span>
            </div>

        </div>
</TabPanel>
    <TabPanel header={t("lesson.resources")}>
                <div className="card">
                    <DataTable value={selectedItem?.resources} tableStyle={{minWidth: '50rem'}} dataKey="id">
                                <Column field="type" header={t("resource.type")}   ></Column>
                                <Column field="url" header={t("resource.url")}   ></Column>
                                <Column field="file" header={t("resource.file")}   ></Column>
                    </DataTable>
                </div>
        </TabPanel>
</TabView>
</Dialog>
);
};
export default View;
