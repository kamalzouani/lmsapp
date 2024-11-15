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

import  {PaymentDto}  from '@/controller/model/student/Payment.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type PaymentViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: PaymentDto,
    t: TFunction
}

const View: React.FC<PaymentViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<PaymentDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("payment.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("payment.tabPan")}>
    <div className="formgrid grid">

                <div className="field col-6">
                    <label htmlFor="student">{t("payment.student")}</label>
                    <InputText  id="studentDropdown"  value={selectedItem?.student?.email}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="course">{t("payment.course")}</label>
                    <InputText  id="courseDropdown"  value={selectedItem?.course?.label}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="amount">{t("payment.amount")}</label>
                    <InputNumber id="amount" value={selectedItem.amount} disabled/>
                </div>

        <div className="field col-6">
            <label htmlFor="paymentDate">{t("payment.paymentDate")}</label>
            <Calendar id="paymentDate" value={adaptDate(selectedItem?.paymentDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

                <div className="field col-6">
                    <label htmlFor="paymentState">{t("payment.paymentState")}</label>
                    <InputText  id="paymentStateDropdown"  value={selectedItem?.paymentState?.label}  disabled  />
                </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
