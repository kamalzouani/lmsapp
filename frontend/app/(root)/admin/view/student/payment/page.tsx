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


import {PaymentAdminService} from '@/controller/service/admin/student/PaymentAdminService.service';
import {PaymentDto}  from '@/controller/model/student/Payment.model';
import {PaymentCriteria} from '@/controller/criteria/student/PaymentCriteria.model';

import {StudentDto} from '@/controller/model/student/Student.model';
import {StudentAdminService} from '@/controller/service/admin/student/StudentAdminService.service';
import {CourseDto} from '@/controller/model/course/Course.model';
import {CourseAdminService} from '@/controller/service/admin/course/CourseAdminService.service';
import {PaymentStateDto} from '@/controller/model/student/PaymentState.model';
import {PaymentStateAdminService} from '@/controller/service/admin/student/PaymentStateAdminService.service';


import Edit from '@/components/admin/view/student/payment/edit/payment-edit-admin.component';
import Create from '@/components/admin/view/student/payment/create/payment-create-admin.component';
import View from '@/components/admin/view/student/payment/view/payment-view-admin.component';



const List = () => {

    const { t } = useTranslation();

    const emptyItem = new PaymentDto();
    const emptyCriteria = new PaymentCriteria();
    const service = new PaymentAdminService();


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
        filter,
        activateSecurityConstraint,
        listPremisions,
    } = useListHook<PaymentDto, PaymentCriteria>({ emptyItem, emptyCriteria,service, t})



    const [students, setStudents] = useState<StudentDto[]>([]);
    const [courses, setCourses] = useState<CourseDto[]>([]);
    const [paymentStates, setPaymentStates] = useState<PaymentStateDto[]>([]);

    const studentAdminService = new StudentAdminService();
    const courseAdminService = new CourseAdminService();
    const paymentStateAdminService = new PaymentStateAdminService();

    useEffect(() => {
        activateSecurityConstraint("Payment")
        studentAdminService.getList().then(({data}) => setStudents(data)).catch(error => console.log(error));
        courseAdminService.getList().then(({data}) => setCourses(data)).catch(error => console.log(error));
        paymentStateAdminService.getList().then(({data}) => setPaymentStates(data)).catch(error => console.log(error));
        fetchItems(criteria);
    }, []);



    const header = (
    <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
        <h5 className="m-0">{t("payment.header")}</h5>
        <span className="block mt-2 md:mt-0 p-input-icon-left"><i className="pi pi-search"/>
            <InputText type="search" onInput={(e) => filter(e)}
                       placeholder={t("search")}/> </span>
    </div>
    );
    return (
    <div className="grid crud-demo">
        {listPremisions ?(
        <div className="col-12">
            <div className="card">
                <Toast ref={toast} />
                <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>
                {findByCriteriaShow && (
                <Card title={t("search")} className="mb-5">
                        <div className="grid">
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="1">{t("payment.studentPlaceHolder")}</label>
                                <Dropdown id="1" value={criteria.student} options={students} onChange={(e) => setCriteria({ ...criteria, student: e.target.value })} optionLabel="email" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="2">{t("payment.coursePlaceHolder")}</label>
                                <Dropdown id="2" value={criteria.course} options={courses} onChange={(e) => setCriteria({ ...criteria, course: e.target.value })} optionLabel="label" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3-1">{t("payment.amountMin")}</label>
                                <InputNumber id="3-1" value={criteria.amountMin} onChange={(e) => setCriteria({ ...criteria, amountMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3-2">{t("payment.amountMax")}  </label>
                                <InputNumber id="3-2" value={criteria.amountMax} onChange={(e) => setCriteria({ ...criteria, amountMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4-1">{t("payment.paymentDateMin")}</label>
                                <Calendar id="4-1" value={criteria.paymentDateFrom} onChange={(e) => setCriteria({ ...criteria, paymentDateFrom: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4-2">{t("payment.paymentDateMax")}</label>
                                <Calendar id="4-2" value={criteria.paymentDateTo} onChange={(e) => setCriteria({ ...criteria, paymentDateTo: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="5">{t("payment.paymentStatePlaceHolder")}</label>
                                <Dropdown id="5" value={criteria.paymentState} options={paymentStates} onChange={(e) => setCriteria({ ...criteria, paymentState: e.target.value })} optionLabel="label" filter showClear/>
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
                    
                    <Column field="student.email" header={t("payment.student")} sortable ></Column>
                    
                    
                    <Column field="course.label" header={t("payment.course")} sortable ></Column>
                    
                    
                    <Column field="amount" header={t("payment.amount")} sortable></Column>
                    
                    
                    <Column field="paymentDate" header={t("payment.paymentDate")} sortable body={formateDate("paymentDate")}></Column>
                    
                    
                    <Column field="paymentState.label" header={t("payment.paymentState")} sortable ></Column>
                    
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
                    {item && (<span>{t("payment.deletePaymentConfirmationMessage")} <b>{item.id}</b>?</span>)}
                    </div>
                </Dialog>

            <Dialog visible={deleteItemsDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteItemsDialogFooter} onHide={hideDeleteItemsDialog} >
                <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                    {item && <span>{t("payment.deletePaymentsConfirmationMessage")}</span>}
                </div>
            </Dialog>

            </div>
        </div>

        ):(
        <div className="card" style={{alignItems:"center",display:"flex",justifyContent:"center"}}>
            <p style={{alignItems:"center",display:"flex",justifyContent:"center"}}>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" style={{width:"90px",height:"90px", color:"red"}}>
                <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v3.75m-9.303 3.376c-.866 1.5.217 3.374 1.948 3.374h14.71c1.73 0 2.813-1.874 1.948-3.374L13.949 3.378c-.866-1.5-3.032-1.5-3.898 0L2.697 16.126ZM12 15.75h.007v.008H12v-.008Z" />
                </svg>
                Opess You don t have permission to access !
            </p>
        </div>
        )}
    </div>
    );
    };
export default List;
