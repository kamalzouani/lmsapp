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

import  {CourseDto}  from '@/controller/model/course/Course.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type CourseViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: CourseDto,
    t: TFunction
}

const View: React.FC<CourseViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<CourseDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("course.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("course.tabPan")}>
    <div className="formgrid grid">

            <div className="field col-6">
                <label htmlFor="code">{t("course.code")}</label>
                <InputText id="code" value={selectedItem?.code} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="label">{t("course.label")}</label>
                <InputText id="label" value={selectedItem?.label} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="description">{t("course.description")}</label>
                <span className="p-float-label">
                   <InputTextarea id="description" value={selectedItem?.description} disabled rows={5} cols={30} />
                </span>
            </div>

        <div className="field col-6">
            <label htmlFor="startDate">{t("course.startDate")}</label>
            <Calendar id="startDate" value={adaptDate(selectedItem?.startDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

        <div className="field col-6">
            <label htmlFor="endDate">{t("course.endDate")}</label>
            <Calendar id="endDate" value={adaptDate(selectedItem?.endDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

                <div className="field col-6">
                    <label htmlFor="duration">{t("course.duration")}</label>
                    <InputNumber id="duration" value={selectedItem.duration} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="price">{t("course.price")}</label>
                    <InputNumber id="price" value={selectedItem.price} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="instructor">{t("course.instructor")}</label>
                    <InputText  id="instructorDropdown"  value={selectedItem?.instructor?.email}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="category">{t("course.category")}</label>
                    <InputText  id="categoryDropdown"  value={selectedItem?.category?.label}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="level">{t("course.level")}</label>
                    <InputText  id="levelDropdown"  value={selectedItem?.level?.label}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="language">{t("course.language")}</label>
                    <InputText  id="languageDropdown"  value={selectedItem?.language?.label}  disabled  />
                </div>
            <div className="field col-6">
                <label htmlFor="requirements">{t("course.requirements")}</label>
                <InputText id="requirements" value={selectedItem?.requirements} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="learningOutcomes">{t("course.learningOutcomes")}</label>
                <InputText id="learningOutcomes" value={selectedItem?.learningOutcomes} disabled   />
            </div>

                <div className="field col-6">
                    <label htmlFor="curriculum">{t("course.curriculum")}</label>
                    <InputText  id="curriculumDropdown"  value={selectedItem?.curriculum?.id}  disabled  />
                </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
