import {Column} from 'primereact/column';
import {TabPanel, TabView} from 'primereact/tabview';
import {DataTable, DataTableExpandedRows, DataTableRowToggleEvent} from 'primereact/datatable';
import {Dialog} from 'primereact/dialog';
import {InputNumber} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {InputTextarea} from 'primereact/inputtextarea';
import React, { useState } from 'react';
import {Calendar} from 'primereact/calendar';
import {InputSwitch} from 'primereact/inputswitch';
import {TFunction} from "i18next";

import  {UserDto}  from '@/utils/zynerator/security/model/User.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type UserViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: UserDto,
    t: TFunction
}

const View: React.FC<UserViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<UserDto>({selectedItem, onClose})
    const [expandedRows, setExpandedRows] = useState<DataTableExpandedRows[]>([]);

    const actionBodyTemplate = (rowData:any) => {
        return (
            <div className="flex align-items-center gap-2 ">


                <InputSwitch  className='ml-4' id={rowData.actionPermission.reference} checked={rowData.value as boolean}  disabled/>


            </div>
        );
    };    const headerTemplate = (modelPermissionUsers:any) => {
        return (
            <React.Fragment>


                <span className="vertical-align-middle ml-2 font-bold line-height-3">{modelPermissionUsers?.modelPermission?.reference}</span>
                                        {/* <InputSwitch className='ml-8 vertical-align-middle  font-bold line-height-3'  id="passwordChanged" checked={modelPermissionUsers?.value  as boolean} onChange={(e) => onBooleanInputChange(e, 'passwordChanged')} /> */}

            </React.Fragment>
        );
    };
        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("user.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("user.tabPan")}>
    <div className="formgrid grid">

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="credentialsNonExpired">{t("user.credentialsNonExpired")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="credentialsNonExpired" checked={selectedItem?.credentialsNonExpired as boolean} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="enabled">{t("user.enabled")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="enabled" checked={selectedItem?.enabled as boolean} disabled />
                    </span>
            </div>
            </div>

            <div className="field col-6">
                <label htmlFor="email">{t("user.email")}</label>
                <InputText id="email" value={selectedItem?.email} disabled   />
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="accountNonExpired">{t("user.accountNonExpired")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="accountNonExpired" checked={selectedItem?.accountNonExpired as boolean} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="accountNonLocked">{t("user.accountNonLocked")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="accountNonLocked" checked={selectedItem?.accountNonLocked as boolean} disabled />
                    </span>
            </div>
            </div>

            <div className="field col-6">
                <label htmlFor="username">{t("user.username")}</label>
                <InputText id="username" value={selectedItem?.username} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="password">{t("user.password")}</label>
                <InputText id="password" value={selectedItem?.password} disabled   />
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="passwordChanged">{t("user.passwordChanged")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="passwordChanged" checked={selectedItem?.passwordChanged as boolean} disabled />
                    </span>
            </div>
            </div>

            <div className="field col-6">
                <label htmlFor="lastName">{t("user.lastName")}</label>
                <InputText id="lastName" value={selectedItem?.lastName} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="firstName">{t("user.firstName")}</label>
                <InputText id="firstName" value={selectedItem?.firstName} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="phone">{t("user.phone")}</label>
                <InputText id="phone" value={selectedItem?.phone} disabled   />
            </div>

        </div>
</TabPanel>
    <TabPanel header={t("user.modelPermissionUsers")}>
    <DataTable value={selectedItem.modelPermissionUsers as any} rowGroupMode="subheader" groupRowsBy="modelPermission.reference"
                    sortMode="single" sortField="modelPermission.reference" sortOrder={1}
                    expandableRowGroups expandedRows={expandedRows} onRowToggle={(e: DataTableRowToggleEvent) => setExpandedRows(e.data as any)}
                    rowGroupHeaderTemplate={headerTemplate}  tableStyle={{ minWidth: '50rem' }}>
                <Column field="" header="Model Permission" style={{ width: '20%' }}>ssss</Column>
                {/* <Column field="country" header="Country" body={countryBodyTemplate} style={{ width: '20%' }}></Column> */}
                <Column field="actionPermission.reference" header="Action Permission" style={{ width: '20%' }} >yyzyzyyzsq</Column>
                {/* <Column field="status" header="Status" body={statusBodyTemplate} style={{ width: '20%' }}></Column> */}
                <Column field="date" header="Value" style={{ width: '20%' }} body={actionBodyTemplate}>ssss</Column>
            </DataTable>
        </TabPanel>
</TabView>
</Dialog>
);
};
export default View;
