import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {TabView, TabPanel} from 'primereact/tabview';
import {Dialog} from 'primereact/dialog';
import {InputNumber, InputNumberChangeEvent} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {classNames} from 'primereact/utils';
import { InputTextarea } from 'primereact/inputtextarea';
import React, {useEffect, useState} from 'react';
import { Calendar } from 'primereact/calendar';
import { format } from 'date-fns';
import {InputSwitch, InputSwitchChangeEvent} from 'primereact/inputswitch';
import {Dropdown, DropdownChangeEvent} from 'primereact/dropdown';
import {MultiSelect, MultiSelectChangeEvent} from 'primereact/multiselect';
import {MessageService} from '@/utils/zynerator/service/MessageService';

import {PaymentAdminService} from '@/controller/service/admin/student/PaymentAdminService.service';
import  {PaymentDto}  from '@/controller/model/student/Payment.model';
import {PaymentCriteria} from "@/controller/criteria/student/PaymentCriteria.model";

import {PaymentStateDto} from '@/controller/model/student/PaymentState.model';
import {PaymentStateAdminService} from '@/controller/service/admin/student/PaymentStateAdminService.service';
import {StudentDto} from '@/controller/model/student/Student.model';
import {StudentAdminService} from '@/controller/service/admin/student/StudentAdminService.service';
import {CourseDto} from '@/controller/model/course/Course.model';
import {CourseAdminService} from '@/controller/service/admin/course/CourseAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type PaymentCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: PaymentDto) => void,
    showToast: React.Ref<Toast>,
    list: PaymentDto[],
    service: PaymentAdminService,
    t: TFunction
}
const Create: React.FC<PaymentCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


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
            onTabChange,
            onDropdownChange,
            hideDialog,
            saveItem,
            formateDate
        } = useCreateHook<PaymentDto, PaymentCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
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
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("payment.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("payment.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="student">{t("payment.student")}</label>
                        <Dropdown  id="studentDropdown"  value={item.student} options={students} onChange={(e) => onDropdownChange(e, 'student')}   placeholder={t("payment.studentPlaceHolder")} filter filterPlaceholder={t("payment.studentPlaceHolderFilter")} optionLabel="email" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="course">{t("payment.course")}</label>
                        <Dropdown  id="courseDropdown"  value={item.course} options={courses} onChange={(e) => onDropdownChange(e, 'course')}   placeholder={t("payment.coursePlaceHolder")} filter filterPlaceholder={t("payment.coursePlaceHolderFilter")} optionLabel="label" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="amount">{t("payment.amount")}</label>
                        <InputNumber id="amount" value={item.amount} onChange={(e) => onInputNumerChange(e, 'amount')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="paymentDate">{t("payment.paymentDate")}</label>
                        <Calendar id="paymentDate" value={item.paymentDate} onChange={(e) => onInputDateChange(e, 'paymentDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="paymentState">{t("payment.paymentState")}</label>
                        <Dropdown  id="paymentStateDropdown"  value={item.paymentState} options={paymentStates} onChange={(e) => onDropdownChange(e, 'paymentState')}   placeholder={t("payment.paymentStatePlaceHolder")} filter filterPlaceholder={t("payment.paymentStatePlaceHolderFilter")} optionLabel="label" showClear/>
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
