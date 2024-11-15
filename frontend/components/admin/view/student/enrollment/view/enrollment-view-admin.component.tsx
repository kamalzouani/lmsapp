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

import  {EnrollmentDto}  from '@/controller/model/student/Enrollment.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type EnrollmentViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: EnrollmentDto,
    t: TFunction
}

const View: React.FC<EnrollmentViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<EnrollmentDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("enrollment.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("enrollment.tabPan")}>
    <div className="formgrid grid">

                <div className="field col-6">
                    <label htmlFor="student">{t("enrollment.student")}</label>
                    <InputText  id="studentDropdown"  value={selectedItem?.student?.email}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="course">{t("enrollment.course")}</label>
                    <InputText  id="courseDropdown"  value={selectedItem?.course?.label}  disabled  />
                </div>
        <div className="field col-6">
            <label htmlFor="enrollmentDate">{t("enrollment.enrollmentDate")}</label>
            <Calendar id="enrollmentDate" value={adaptDate(selectedItem?.enrollmentDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

                <div className="field col-6">
                    <label htmlFor="enrollmentState">{t("enrollment.enrollmentState")}</label>
                    <InputText  id="enrollmentStateDropdown"  value={selectedItem?.enrollmentState?.label}  disabled  />
                </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
