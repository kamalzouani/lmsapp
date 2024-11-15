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

import {RoleDto} from "@/utils/zynerator/dto/RoleDto.model";
import  {StudentDto}  from '@/controller/model/student/Student.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type StudentViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: StudentDto,
    t: TFunction
}

const View: React.FC<StudentViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<StudentDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("student.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("student.tabPan")}>
    <div className="formgrid grid">

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="credentialsNonExpired">{t("student.credentialsNonExpired")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="credentialsNonExpired" checked={selectedItem?.credentialsNonExpired} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="accountNonExpired">{t("student.accountNonExpired")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="accountNonExpired" checked={selectedItem?.accountNonExpired} disabled />
                    </span>
            </div>
            </div>

            <div className="field col-6">
                <label htmlFor="username">{t("student.username")}</label>
                <InputText id="username" value={selectedItem?.username} disabled   />
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="passwordChanged">{t("student.passwordChanged")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="passwordChanged" checked={selectedItem?.passwordChanged} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="accountNonLocked">{t("student.accountNonLocked")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="accountNonLocked" checked={selectedItem?.accountNonLocked} disabled />
                    </span>
            </div>
            </div>

                <div className="field col-6">
                    <label htmlFor="password">{t("student.password")}</label>
                    <InputNumber id="password" value={selectedItem.password} disabled/>
                </div>

            <div className="field col-6">
                <label htmlFor="email">{t("student.email")}</label>
                <InputText id="email" value={selectedItem?.email} disabled   />
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="enabled">{t("student.enabled")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="enabled" checked={selectedItem?.enabled} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <label htmlFor="roles">Roles</label>
            <InputText id="roles" value={selectedItem?.roles?.map(e=>e.label).join(",")} disabled   />
        </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
