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

import  {ModelPermissionUserDto}  from '@/utils/zynerator/security/model/ModelPermissionUser.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type ModelPermissionUserViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: ModelPermissionUserDto,
    t: TFunction
}

const View: React.FC<ModelPermissionUserViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<ModelPermissionUserDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("modelPermissionUser.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("modelPermissionUser.tabPan")}>
    <div className="formgrid grid">

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="value">{t("modelPermissionUser.value")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="value" checked={selectedItem?.value as boolean} disabled />
                    </span>
            </div>
            </div>

            <div className="field col-6">
                <label htmlFor="subAttribute">{t("modelPermissionUser.subAttribute")}</label>
                <InputText id="subAttribute" value={selectedItem?.subAttribute} disabled   />
            </div>

                <div className="field col-6">
                    <label htmlFor="actionPermission">{t("modelPermissionUser.actionPermission")}</label>
                    <InputText  id="actionPermissionDropdown"  value={selectedItem?.actionPermission?.id as any}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="modelPermission">{t("modelPermissionUser.modelPermission")}</label>
                    <InputText  id="modelPermissionDropdown"  value={selectedItem?.modelPermission?.id as any}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="user">{t("modelPermissionUser.user")}</label>
                    <InputText  id="userDropdown"  value={selectedItem?.user?.id as any}  disabled  />
                </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
