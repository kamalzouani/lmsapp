"use client"
import {Button} from 'primereact/button';
import {Column} from 'primereact/column';


import {DataTable} from 'primereact/datatable';
import {Dialog} from 'primereact/dialog';
import {FileUpload} from 'primereact/fileupload';
import {InputText} from 'primereact/inputtext';
import {Toast} from 'primereact/toast';
import {Toolbar} from 'primereact/toolbar';
import React, {useEffect, useRef, useState} from 'react';
import {Paginator, PaginatorPageChangeEvent} from 'primereact/paginator';
import {Card} from 'primereact/card';
import {Calendar} from 'primereact/calendar';
import {InputNumber} from 'primereact/inputnumber';
import {Dropdown} from 'primereact/dropdown';
import {format} from "date-fns";
import { useTranslation } from 'react-i18next';

import useListHook from "@/utils/zyhook/useListhook";


import {ModelPermissionUserAdminService} from '@/utils/zynerator/security/service/ModelPermissionUserAdminService.service';
import {ModelPermissionUserDto}  from '@/utils/zynerator/security/model/ModelPermissionUser.model';
import {ModelPermissionUserCriteria} from '@/utils/zynerator/security/criteria/ModelPermissionUserCriteria.model';

import {ActionPermissionDto} from '@/utils/zynerator/security/model/ActionPermission.model';
import {ActionPermissionAdminService} from '@/utils/zynerator/security/service/ActionPermissionAdminService.service';
import {ModelPermissionDto} from '@/utils/zynerator/security/model/ModelPermission.model';
import {ModelPermissionAdminService} from '@/utils/zynerator/security/service/ModelPermissionAdminService.service';
import {UserDto} from '@/utils/zynerator/security/model/User.model';
import {UserAdminService} from '@/utils/zynerator/security/service/UserAdminService.service';


import Edit from '@/components/security/model-permission-user/edit/model-permission-user-edit-admin.component';
import Create from '@/components/security/model-permission-user/create/model-permission-user-create-admin.component';
import View from '@/components/security/model-permission-user/view/model-permission-user-view-admin.component';



const List = () => {

    const { t } = useTranslation();

    const emptyItem = new ModelPermissionUserDto();
    const emptyCriteria = new ModelPermissionUserCriteria();
    const service = new ModelPermissionUserAdminService();


    const {
        items,
        showSearch,
        deleteItemDialog,
        item,
        selectedItems,
        setSelectedItems,
        hideDeleteItemDialog,
        globalFilter,
        setGlobalFilter,
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
        confirmDeleteSelected,
        exportCSV,
        deleteItem,
        deleteItemDialogFooter,
        leftToolbarTemplate,
        rightToolbarTemplate,
        actionBodyTemplate,
        CustomBooleanCell,
        handleValidateClick,
        onPage,
        showCreateModal,
        showEditModal,
        showViewModal,
        add,
        update,
        confirmDeleteItem,
        statusBodyTemplate,
        formateDate,
        deleteSelectedItems,
        deleteItemsDialog,
        deleteItemsDialogFooter,
        hideDeleteItemsDialog,
        filter
    } = useListHook<ModelPermissionUserDto, ModelPermissionUserCriteria>({ emptyItem, emptyCriteria,service, t})



    const [actionPermissions, setActionPermissions] = useState<ActionPermissionDto[]>([]);
    const [modelPermissions, setModelPermissions] = useState<ModelPermissionDto[]>([]);
    const [users, setUsers] = useState<UserDto[]>([]);

    const actionPermissionAdminService = new ActionPermissionAdminService();
    const modelPermissionAdminService = new ModelPermissionAdminService();
    const userAdminService = new UserAdminService();

    useEffect(() => {

        actionPermissionAdminService.getList().then(({data}) => setActionPermissions(data)).catch(error => console.log(error));
        modelPermissionAdminService.getList().then(({data}) => setModelPermissions(data)).catch(error => console.log(error));
        userAdminService.getList().then(({data}) => setUsers(data)).catch(error => console.log(error));
        fetchItems(criteria);
    }, []);



    const header = (
    <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
        <h5 className="m-0">{t("modelPermissionUser.header")}</h5>
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
                                <label className="mb-1"  htmlFor="2">{t("modelPermissionUser.subAttribute")}</label>
                                <InputText id="2" value={criteria.subAttribute} onChange={(e) => setCriteria({ ...criteria, subAttribute: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3">{t("modelPermissionUser.actionPermissionPlaceHolder")}</label>
                                <Dropdown id="3" value={criteria.actionPermission} options={actionPermissions} onChange={(e) => setCriteria({ ...criteria, actionPermission: e.target.value })} optionLabel="id" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4">{t("modelPermissionUser.modelPermissionPlaceHolder")}</label>
                                <Dropdown id="4" value={criteria.modelPermission} options={modelPermissions} onChange={(e) => setCriteria({ ...criteria, modelPermission: e.target.value })} optionLabel="id" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="5">{t("modelPermissionUser.userPlaceHolder")}</label>
                                <Dropdown id="5" value={criteria.user} options={users} onChange={(e) => setCriteria({ ...criteria, user: e.target.value })} optionLabel="id" filter showClear/>
                            </div>
                        </div>
                        <div style={{ marginTop : '1rem', display: 'flex', justifyContent: 'flex-end' }} >
                            <Button label={t("validate")} icon="pi pi-sort-amount-down" className="p-button-info mr-2" onClick={handleValidateClick} />
                            <Button label={t("cancel")} className="p-button-secondary mr-2"  icon="pi pi-times" onClick={handleCancelClick} />
                        </div>
                </Card>
                )}
                <DataTable ref={dt} value={items} selection={selectedItems as any} onSelectionChange={(e) => setSelectedItems(e.value as any)} dataKey="id" className="datatable-responsive" filters={globalFilter} header={header} responsiveLayout="scroll" >
                    <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}> </Column>

                    <Column field="value" header={t("modelPermissionUser.value")} body={CustomBooleanCell("value")} sortable></Column>


                    <Column field="subAttribute" header={t("modelPermissionUser.subAttribute")} sortable></Column>


                    <Column field="actionPermission.id" header={t("modelPermissionUser.actionPermission")} sortable ></Column>


                    <Column field="modelPermission.id" header={t("modelPermissionUser.modelPermission")} sortable ></Column>


                    <Column field="user.id" header={t("modelPermissionUser.user")} sortable ></Column>

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
                    {item && (<span>{t("modelPermissionUser.deleteModelPermissionUserConfirmationMessage")} <b>{item.id}</b>?</span>)}
                    </div>
                </Dialog>

            <Dialog visible={deleteItemsDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteItemsDialogFooter} onHide={hideDeleteItemsDialog} >
                <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                    {item && <span>{t("modelPermissionUser.deleteModelPermissionUsersConfirmationMessage")}</span>}
                </div>
            </Dialog>

            </div>
        </div>
    </div>
);
};
export default List;

