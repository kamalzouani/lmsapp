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

import {EnrollmentAdminService} from '@/controller/service/admin/student/EnrollmentAdminService.service';
import  {EnrollmentDto}  from '@/controller/model/student/Enrollment.model';
import {EnrollmentCriteria} from "@/controller/criteria/student/EnrollmentCriteria.model";

import {EnrollmentStateDto} from '@/controller/model/student/EnrollmentState.model';
import {EnrollmentStateAdminService} from '@/controller/service/admin/student/EnrollmentStateAdminService.service';
import {StudentDto} from '@/controller/model/student/Student.model';
import {StudentAdminService} from '@/controller/service/admin/student/StudentAdminService.service';
import {CourseDto} from '@/controller/model/course/Course.model';
import {CourseAdminService} from '@/controller/service/admin/course/CourseAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type EnrollmentCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: EnrollmentDto) => void,
    showToast: React.Ref<Toast>,
    list: EnrollmentDto[],
    service: EnrollmentAdminService,
    t: TFunction
}
const Create: React.FC<EnrollmentCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        return errorMessages.length == 0 ;
    }
    const emptyItem = new EnrollmentDto();
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
        } = useCreateHook<EnrollmentDto, EnrollmentCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [courses, setCourses] = useState<CourseDto[]>([]);
    const [students, setStudents] = useState<StudentDto[]>([]);
    const [enrollmentStates, setEnrollmentStates] = useState<EnrollmentStateDto[]>([]);


    const enrollmentStateAdminService = new EnrollmentStateAdminService();
    const studentAdminService = new StudentAdminService();
    const courseAdminService = new CourseAdminService();
    useEffect(() => {
        studentAdminService.getList().then(({data}) => setStudents(data)).catch(error => console.log(error));
        courseAdminService.getList().then(({data}) => setCourses(data)).catch(error => console.log(error));
        enrollmentStateAdminService.getList().then(({data}) => setEnrollmentStates(data)).catch(error => console.log(error));
    }, []);








    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("enrollment.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("enrollment.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="student">{t("enrollment.student")}</label>
                        <Dropdown  id="studentDropdown"  value={item.student} options={students} onChange={(e) => onDropdownChange(e, 'student')}   placeholder={t("enrollment.studentPlaceHolder")} filter filterPlaceholder={t("enrollment.studentPlaceHolderFilter")} optionLabel="email" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="course">{t("enrollment.course")}</label>
                        <Dropdown  id="courseDropdown"  value={item.course} options={courses} onChange={(e) => onDropdownChange(e, 'course')}   placeholder={t("enrollment.coursePlaceHolder")} filter filterPlaceholder={t("enrollment.coursePlaceHolderFilter")} optionLabel="label" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="enrollmentDate">{t("enrollment.enrollmentDate")}</label>
                        <Calendar id="enrollmentDate" value={item.enrollmentDate} onChange={(e) => onInputDateChange(e, 'enrollmentDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="enrollmentState">{t("enrollment.enrollmentState")}</label>
                        <Dropdown  id="enrollmentStateDropdown"  value={item.enrollmentState} options={enrollmentStates} onChange={(e) => onDropdownChange(e, 'enrollmentState')}   placeholder={t("enrollment.enrollmentStatePlaceHolder")} filter filterPlaceholder={t("enrollment.enrollmentStatePlaceHolderFilter")} optionLabel="label" showClear/>
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
