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

import {ReviewAdminService} from '@/controller/service/admin/student/ReviewAdminService.service';
import  {ReviewDto}  from '@/controller/model/student/Review.model';
import {ReviewCriteria} from "@/controller/criteria/student/ReviewCriteria.model";

import {StudentDto} from '@/controller/model/student/Student.model';
import {StudentAdminService} from '@/controller/service/admin/student/StudentAdminService.service';
import {CourseDto} from '@/controller/model/course/Course.model';
import {CourseAdminService} from '@/controller/service/admin/course/CourseAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type ReviewCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: ReviewDto) => void,
    showToast: React.Ref<Toast>,
    list: ReviewDto[],
    service: ReviewAdminService,
    t: TFunction
}
const Create: React.FC<ReviewCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        return errorMessages.length == 0 ;
    }
    const emptyItem = new ReviewDto();
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
        } = useCreateHook<ReviewDto, ReviewCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [courses, setCourses] = useState<CourseDto[]>([]);
    const [students, setStudents] = useState<StudentDto[]>([]);


    const studentAdminService = new StudentAdminService();
    const courseAdminService = new CourseAdminService();
    useEffect(() => {
        studentAdminService.getList().then(({data}) => setStudents(data)).catch(error => console.log(error));
        courseAdminService.getList().then(({data}) => setCourses(data)).catch(error => console.log(error));
    }, []);








    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("review.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("review.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="student">{t("review.student")}</label>
                        <Dropdown  id="studentDropdown"  value={item.student} options={students} onChange={(e) => onDropdownChange(e, 'student')}   placeholder={t("review.studentPlaceHolder")} filter filterPlaceholder={t("review.studentPlaceHolderFilter")} optionLabel="email" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="course">{t("review.course")}</label>
                        <Dropdown  id="courseDropdown"  value={item.course} options={courses} onChange={(e) => onDropdownChange(e, 'course')}   placeholder={t("review.coursePlaceHolder")} filter filterPlaceholder={t("review.coursePlaceHolderFilter")} optionLabel="label" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="rating">{t("review.rating")}</label>
                        <InputNumber id="rating" value={item.rating} onChange={(e) => onInputNumerChange(e, 'rating')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="comment">{t("review.comment")}</label>
                        <span className="p-float-label">
                        <InputTextarea id="comment" value={item.comment} onChange={(e) => onInputTextChange(e, 'comment')} rows={5} cols={30}/>
                    </span>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="reviewDate">{t("review.reviewDate")}</label>
                        <Calendar id="reviewDate" value={item.reviewDate} onChange={(e) => onInputDateChange(e, 'reviewDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
