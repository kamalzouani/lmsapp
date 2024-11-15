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
import  {InstructorDto}  from '@/controller/model/instructor/Instructor.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type InstructorViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: InstructorDto,
    t: TFunction
}

const View: React.FC<InstructorViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<InstructorDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("instructor.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("instructor.tabPan")}>
    <div className="formgrid grid">

            <div className="field col-6">
                <label htmlFor="bio">{t("instructor.bio")}</label>
                <span className="p-float-label">
                   <InputTextarea id="bio" value={selectedItem?.bio} disabled rows={5} cols={30} />
                </span>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="credentialsNonExpired">{t("instructor.credentialsNonExpired")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="credentialsNonExpired" checked={selectedItem?.credentialsNonExpired} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="accountNonExpired">{t("instructor.accountNonExpired")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="accountNonExpired" checked={selectedItem?.accountNonExpired} disabled />
                    </span>
            </div>
            </div>

            <div className="field col-6">
                <label htmlFor="username">{t("instructor.username")}</label>
                <InputText id="username" value={selectedItem?.username} disabled   />
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="passwordChanged">{t("instructor.passwordChanged")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="passwordChanged" checked={selectedItem?.passwordChanged} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="accountNonLocked">{t("instructor.accountNonLocked")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="accountNonLocked" checked={selectedItem?.accountNonLocked} disabled />
                    </span>
            </div>
            </div>

                <div className="field col-6">
                    <label htmlFor="password">{t("instructor.password")}</label>
                    <InputNumber id="password" value={selectedItem.password} disabled/>
                </div>

            <div className="field col-6">
                <label htmlFor="email">{t("instructor.email")}</label>
                <InputText id="email" value={selectedItem?.email} disabled   />
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="enabled">{t("instructor.enabled")}</label>
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
