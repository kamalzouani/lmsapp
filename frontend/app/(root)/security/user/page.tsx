"use client"
import {Button} from 'primereact/button';
import {Column} from 'primereact/column';


import {DataTable} from 'primereact/datatable';
import {Dialog} from 'primereact/dialog';
import {InputText} from 'primereact/inputtext';
import {Toast} from 'primereact/toast';
import {Toolbar} from 'primereact/toolbar';
import React, {useEffect, useState} from 'react';
import {Paginator} from 'primereact/paginator';
import {Card} from 'primereact/card';

import { useTranslation } from 'react-i18next';

import useListHook from "@/utils/zyhook/useListhook";


import {UserAdminService} from '@/utils/zynerator/security/service/UserAdminService.service';
import {UserDto}  from '@/utils/zynerator/security/model/User.model';
import {UserCriteria} from '@/utils/zynerator/security/criteria/UserCriteria.model';


import Create from '@/components/security/user/create/user-create-admin.component';
import Edit from '@/components/security/user/edit/user-edit-admin.component';
import View from '@/components/security/user/view/user-view-admin.component';
import { ModelPermissionDto } from '@/utils/zynerator/security/model/ModelPermission.model';






const List = () => {

    const { t } = useTranslation();

    const emptyItem = new UserDto();
    const emptyCriteria = new UserCriteria();
    const service = new UserAdminService();


    const {
        items,

        deleteItemDialog,
        item,
        selectedItems,
        setSelectedItems,
        hideDeleteItemDialog,
        globalFilter,
        showCreateDialog,
        setShowCreateDialog,
        showEditDialog,
        setShowEditDialog,
        showViewDialog,
        setShowViewDialog,
        selectedItem,
        setSelectedItem,
        rows,
        totalRecords,
        criteria,
        setCriteria,
        first,
        fetchItems,
        toast,
        dt,
        findByCriteriaShow,
        handleCancelClick,
        deleteItemDialogFooter,
        leftToolbarTemplate,
        rightToolbarTemplate,
        actionBodyTemplate,
        CustomBooleanCell,
        handleValidateClick,
        onPage,

        add,
        update,

        deleteItemsDialog,
        deleteItemsDialogFooter,
        hideDeleteItemsDialog,
        filter
    } = useListHook<UserDto, UserCriteria>({ emptyItem, emptyCriteria,service, t})



    useEffect(() => {

        fetchItems(criteria);
    }, []);



    const header = (
    <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
        <h5 className="m-0">{t("user.header")}</h5>
        <span className="block mt-2 md:mt-0 p-input-icon-left"><i className="pi pi-search"/>
            <InputText type="search" onInput={(e) => filter(e)}
                       placeholder={t("search")}/> </span>
    </div>
    );
    return (
    <div className="grid crud-demo">
        <div className="col-12">
            <div className="card">
                <Toast ref={toast} />
                <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>
                {findByCriteriaShow && (
                <Card title={t("search")} className="mb-5">
                        <div className="grid">
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3">{t("user.email")}</label>
                                <InputText id="3" value={criteria.email} onChange={(e) => setCriteria({ ...criteria, email: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="6">{t("user.username")}</label>
                                <InputText id="6" value={criteria.username} onChange={(e) => setCriteria({ ...criteria, username: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="7">{t("user.password")}</label>
                                <InputText id="7" value={criteria.password} onChange={(e) => setCriteria({ ...criteria, password: e.target.value })} />
                            </div>
                            {/* <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="9">{t("user.lastName")}</label>
                                <InputText id="9" value={criteria.lastName} onChange={(e) => setCriteria({ ...criteria, lastName: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="10">{t("user.firstName")}</label>
                                <InputText id="10" value={criteria.firstName} onChange={(e) => setCriteria({ ...criteria, firstName: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="11">{t("user.phone")}</label>
                                <InputText id="11" value={criteria.phone} onChange={(e) => setCriteria({ ...criteria, phone: e.target.value })} />
                            </div>*/}
                        </div>
                        <div style={{ marginTop : '1rem', display: 'flex', justifyContent: 'flex-end' }} >
                            <Button label={t("validate")} icon="pi pi-sort-amount-down" className="p-button-info mr-2" onClick={handleValidateClick} />
                            <Button label={t("cancel")} className="p-button-secondary mr-2"  icon="pi pi-times" onClick={handleCancelClick} />
                        </div>
                </Card>
                )}
                <DataTable ref={dt} value={items} selection={selectedItems as any} onSelectionChange={(e) => setSelectedItems(e.value as any)} dataKey="id" className="datatable-responsive" filters={globalFilter} header={header} responsiveLayout="scroll" >
                    <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}> </Column>
                    <Column field="username" header={t("user.username")} sortable></Column>
                    <Column field="email" header={t("user.email")} sortable></Column>

                    {/* <Column field="lastName" header={t("user.lastName")} sortable></Column> */}

                    {/*
                    <Column field="firstName" header={t("user.firstName")} sortable></Column>
                     */}
                    {/*
                    <Column field="phone" header={t("user.phone")} sortable></Column>
                     */}
                    <Column field="credentialsNonExpired" header={t("user.credentialsNonExpired")} body={CustomBooleanCell("credentialsNonExpired")} sortable></Column>


                    <Column field="enabled" header={t("user.enabled")} body={CustomBooleanCell("enabled")} sortable></Column>




                    <Column field="accountNonExpired" header={t("user.accountNonExpired")} body={CustomBooleanCell("accountNonExpired")} sortable></Column>


                    <Column field="accountNonLocked" header={t("user.accountNonLocked")} body={CustomBooleanCell("accountNonLocked")} sortable></Column>








                    <Column field="passwordChanged" header={t("user.passwordChanged")} body={CustomBooleanCell("passwordChanged")} sortable></Column>



                    <Column header={t("actions")} body={actionBodyTemplate}></Column>
                </DataTable>
                <div className="p-d-flex p-ai-center p-jc-between">
                    <Paginator onPageChange={onPage} first={first} rows={rows} totalRecords={totalRecords} />
                </div>
                {showCreateDialog && <Create visible={showCreateDialog} onClose={() => setShowCreateDialog(false)} add={add} showToast={toast} list={items} service={service} t={t} />}

                {showEditDialog && <Edit  visible={showEditDialog} onClose={() =>  { setShowEditDialog(false); setSelectedItem(emptyItem); }} showToast={toast} selectedItem={selectedItem} update={update} list={items} service={service}   t={t} />}

                {showViewDialog && <View visible={showViewDialog} onClose={() =>  { setShowViewDialog(false); setSelectedItem(emptyItem); }} selectedItem={selectedItem}   t={t} />}
                <Dialog visible={deleteItemDialog} style={{width: '450px'}} header={t("confirm")} modal footer={deleteItemDialogFooter} onHide={hideDeleteItemDialog}>
                    <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{fontSize: '2rem'}}/>
                    {item && (<span>{t("user.deleteUserConfirmationMessage")} <b>{item.id}</b>?</span>)}
                    </div>
                </Dialog>

            <Dialog visible={deleteItemsDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteItemsDialogFooter} onHide={hideDeleteItemsDialog} >
                <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                    {item && <span>{t("user.deleteUsersConfirmationMessage")}</span>}
                </div>
            </Dialog>

            </div>
        </div>
    </div>
);
};
export default List;

