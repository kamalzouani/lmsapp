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

import  {CurriculumDto}  from '@/controller/model/course/Curriculum.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type CurriculumViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: CurriculumDto,
    t: TFunction
}

const View: React.FC<CurriculumViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<CurriculumDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("curriculum.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("curriculum.tabPan")}>
    <div className="formgrid grid">

                <div className="field col-6">
                    <label htmlFor="course">{t("curriculum.course")}</label>
                    <InputText  id="courseDropdown"  value={selectedItem?.course?.label}  disabled  />
                </div>
        </div>
</TabPanel>
    <TabPanel header={t("curriculum.modules")}>
                <div className="card">
                    <DataTable value={selectedItem?.modules} tableStyle={{minWidth: '50rem'}} dataKey="id">
                                <Column field="label" header={t("module.label")}   ></Column>
                                <Column field="description" header={t("module.description")}   ></Column>
                    </DataTable>
                </div>
        </TabPanel>
</TabView>
</Dialog>
);
};
export default View;
