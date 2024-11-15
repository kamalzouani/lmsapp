import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {Dropdown, DropdownChangeEvent} from 'primereact/dropdown';
import {TabView, TabPanel} from 'primereact/tabview';
import {Dialog} from 'primereact/dialog';
import {InputNumber, InputNumberChangeEvent} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {classNames} from 'primereact/utils';
import { InputTextarea } from 'primereact/inputtextarea';
import React, {useEffect, useState} from 'react';
import {Calendar} from 'primereact/calendar';
import { format } from 'date-fns';
import { parse } from 'date-fns';
import { InputSwitch } from 'primereact/inputswitch';
import {MultiSelect, MultiSelectChangeEvent} from 'primereact/multiselect';


import {MessageService} from '@/utils/zynerator/service/MessageService';


import {TFunction} from "i18next";
import {Toast} from "primereact/toast";

import useEditHook from "@/utils/zyhook/useEdit.hook";


import {PaymentAdminService} from '@/controller/service/admin/student/PaymentAdminService.service';
import  {PaymentDto}  from '@/controller/model/student/Payment.model';
import {PaymentCriteria} from "@/controller/criteria/student/PaymentCriteria.model";


import {PaymentStateDto} from '@/controller/model/student/PaymentState.model';
import {PaymentStateAdminService} from '@/controller/service/admin/student/PaymentStateAdminService.service';
import {StudentDto} from '@/controller/model/student/Student.model';
import {StudentAdminService} from '@/controller/service/admin/student/StudentAdminService.service';
import {CourseDto} from '@/controller/model/course/Course.model';
import {CourseAdminService} from '@/controller/service/admin/course/CourseAdminService.service';


type PaymentEditAdminType = {
    visible: boolean,
    onClose: () => void,
    showToast: React.Ref<Toast>,
    selectedItem: PaymentDto
    update: (item: PaymentDto) => void,
    list: PaymentDto[],
    service: PaymentAdminService,
    t: TFunction
}
const Edit: React.FC<PaymentEditAdminType> = ({visible, onClose, showToast, selectedItem, update, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        return errorMessages.length == 0 ;
    }
    const emptyItem = new PaymentDto();


    const {
        item,
        setItem,
        submitted,
        setSubmitted,
        activeIndex,
        setActiveIndex,
        activeTab,
        setActiveTab,
        onInputTextChange,
        onInputDateChange,
        onInputNumerChange,
        onMultiSelectChange,
        onBooleanInputChange,
        onDropdownChange,
        onTabChange,
        hideDialog,
        editItem,
        formateDate,
        parseToIsoFormat,
        adaptDate
        } = useEditHook<PaymentDto, PaymentCriteria>({list, selectedItem, onClose, update, showToast,service, t, isFormValid})

    const [courses, setCourses] = useState<CourseDto[]>([]);
    const [students, setStudents] = useState<StudentDto[]>([]);
    const [paymentStates, setPaymentStates] = useState<PaymentStateDto[]>([]);


    const paymentStateAdminService = new PaymentStateAdminService();
    const studentAdminService = new StudentAdminService();
    const courseAdminService = new CourseAdminService();
    useEffect(() => {
    studentAdminService.getList().then(({data}) => setStudents(data)).catch(error => console.log(error));
    courseAdminService.getList().then(({data}) => setCourses(data)).catch(error => console.log(error));
    paymentStateAdminService.getList().then(({data}) => setPaymentStates(data)).catch(error => console.log(error));


        }, []);







    const itemDialogFooter = ( <>
        <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
        <Button label="Save" icon="pi pi-check" onClick={editItem} /> </>
    );



    return(
    <Dialog visible={visible} style={{width: '70vw'}} header={t("payment.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog}>
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("payment.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="student">{t("payment.student")}</label>
                        <Dropdown  id="studentDropdown"  value={item ? item.student : ''} options={students} onChange={(e) => onDropdownChange(e, 'student')}   placeholder="Sélectionnez un student" filter filterPlaceholder="Rechercher un student" optionLabel="email" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="course">{t("payment.course")}</label>
                        <Dropdown  id="courseDropdown"  value={item ? item.course : ''} options={courses} onChange={(e) => onDropdownChange(e, 'course')}   placeholder="Sélectionnez un course" filter filterPlaceholder="Rechercher un course" optionLabel="label" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="amount">{t("payment.amount")}</label>
                        <InputNumber id="amount" value={item ? item.amount : 0} onChange={(e) => onInputNumerChange(e, 'amount')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="paymentDate">{t("payment.paymentDate")}</label>
                        <Calendar id="paymentDate" value={adaptDate(item?.paymentDate)} onChange={(e) => onInputDateChange(e, 'paymentDate')} dateFormat="dd/mm/yy" showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="paymentState">{t("payment.paymentState")}</label>
                        <Dropdown  id="paymentStateDropdown"  value={item ? item.paymentState : ''} options={paymentStates} onChange={(e) => onDropdownChange(e, 'paymentState')}   placeholder="Sélectionnez un paymentState" filter filterPlaceholder="Rechercher un paymentState" optionLabel="label" showClear />
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Edit;


