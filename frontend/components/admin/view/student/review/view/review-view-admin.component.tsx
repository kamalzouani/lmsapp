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

import  {ReviewDto}  from '@/controller/model/student/Review.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type ReviewViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: ReviewDto,
    t: TFunction
}

const View: React.FC<ReviewViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<ReviewDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("review.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("review.tabPan")}>
    <div className="formgrid grid">

                <div className="field col-6">
                    <label htmlFor="student">{t("review.student")}</label>
                    <InputText  id="studentDropdown"  value={selectedItem?.student?.email}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="course">{t("review.course")}</label>
                    <InputText  id="courseDropdown"  value={selectedItem?.course?.label}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="rating">{t("review.rating")}</label>
                    <InputNumber id="rating" value={selectedItem.rating} disabled/>
                </div>

            <div className="field col-6">
                <label htmlFor="comment">{t("review.comment")}</label>
                <span className="p-float-label">
                   <InputTextarea id="comment" value={selectedItem?.comment} disabled rows={5} cols={30} />
                </span>
            </div>

        <div className="field col-6">
            <label htmlFor="reviewDate">{t("review.reviewDate")}</label>
            <Calendar id="reviewDate" value={adaptDate(selectedItem?.reviewDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
