import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {TabView, TabPanel} from 'primereact/tabview';
import {DataTable, DataTableExpandedRows, DataTableRowToggleEvent} from 'primereact/datatable';
import {Dialog} from 'primereact/dialog';
import {InputText} from 'primereact/inputtext';
import {classNames} from 'primereact/utils';
import React, {useEffect, useState} from 'react';
import {InputSwitch, InputSwitchChangeEvent} from 'primereact/inputswitch';
import {MultiSelect, MultiSelectChangeEvent} from 'primereact/multiselect';
import {MessageService} from '@/utils/zynerator/service/MessageService';

import {UserAdminService} from '@/utils/zynerator/security/service/UserAdminService.service';
import  {UserDto}  from '@/utils/zynerator/security/model/User.model';
import {UserCriteria} from "@/utils/zynerator/security/criteria/UserCriteria.model";

import {RoleDto} from '@/utils/zynerator/security/model/Role.model';
import {RoleAdminService} from '@/utils/zynerator/security/service/RoleAdminService.service';
import {ModelPermissionUserDto} from '@/utils/zynerator/security/model/ModelPermissionUser.model';
import {ModelPermissionUserAdminService} from '@/utils/zynerator/security/service/ModelPermissionUserAdminService.service';
import {ModelPermissionDto} from '@/utils/zynerator/security/model/ModelPermission.model';
import {ModelPermissionAdminService} from '@/utils/zynerator/security/service/ModelPermissionAdminService.service';
import {RoleUserDto} from '@/utils/zynerator/security/model/RoleUser.model';
import {RoleUserAdminService} from '@/utils/zynerator/security/service/RoleUserAdminService.service';
import {ActionPermissionDto} from '@/utils/zynerator/security/model/ActionPermission.model';
import {ActionPermissionAdminService} from '@/utils/zynerator/security/service/ActionPermissionAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type UserCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: UserDto) => void,
    showToast: React.Ref<Toast>,
    list: UserDto[],
    service: UserAdminService,
    t: TFunction

}
const Create: React.FC<UserCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        return errorMessages.length == 0 ;
    }
    const emptyItem = new UserDto();
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
            formateDate,

        } = useCreateHook<UserDto, UserCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [roles, setRoles] = useState<RoleDto[]>([]);
    const [modelPermissions, setModelPermissions] = useState<ModelPermissionDto[]>([]);
    const [actionPermissions, setActionPermissions] = useState<ActionPermissionDto[]>([]);
    const [expandedRows, setExpandedRows] = useState<DataTableExpandedRows[]>([]);
    const [active, setActive] = useState(false);
    const [roleUsers, setRoleUsers] = useState<any>();
    const [modelPermissionUsers, setModelPermissionUsers] = useState<ModelPermissionUserDto>(new ModelPermissionUserDto());

    const roleAdminService = new RoleAdminService();
    const modelPermissionUserAdminService = new ModelPermissionUserAdminService();
    const modelPermissionAdminService = new ModelPermissionAdminService();
    const actionPermissionAdminService = new ActionPermissionAdminService();
    const roleUserAdminService = new RoleUserAdminService();
    useEffect(() => {
        roleAdminService.getList().then(({data}) => {
            const roleUsers = data?.map(prepareRoleUser)
            setRoleUsers(roleUsers as any)
            console.log(roleUsers);

        })


        const prepareRoleUser = (role: RoleDto) => {
            const roleUser = new RoleUserDto();
            roleUser.role = role;

            return roleUser;
        }



        actionPermissionAdminService.getList().then(({data}) => setActionPermissions(data as any)).catch(error => console.log(error));
        modelPermissionAdminService.getList().then(({data}) => {console.log(data);
         setModelPermissions(data as any);}).catch(error => console.log(error));
        modelPermissionUserAdminService.findByUserUserName("admin").then(({data}) => {

                setModelPermissionUsers(data  as any);

            setActive(true)
        }
            ).catch(error => console.log(error));
    }, []);

const saveItems=() => {
    const items=item;
   const itmp= items.modelPermissionUsers.map((items:any) => {
items.id=null
const { user, ...copiedItem } =items
return copiedItem
    })
    items.modelPermissionUsers=itmp;
    setItem(items)
    saveItem()
}


    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItems} /> </>
    );


    const headerTemplate = (modelPermissionUsers:any) => {
        return (
            <React.Fragment>


                <span className="vertical-align-middle ml-2 font-bold line-height-3">{modelPermissionUsers?.modelPermission?.reference}</span>
                                        {/* <InputSwitch className='ml-8 vertical-align-middle  font-bold line-height-3'  id="passwordChanged" checked={modelPermissionUsers?.value  as boolean} onChange={(e) => onBooleanInputChange(e, 'passwordChanged')} /> */}

            </React.Fragment>
        );
    };
    const handleSwitchChange = (rowData :any, checked:any) => {
        const updatedData = (modelPermissionUsers as any).map((items:any) => {
            if (items.id === rowData.id) {
            return { ...items, value: checked };
          }

          return items;
        });
    item.modelPermissionUsers=updatedData;
        setModelPermissionUsers(updatedData);
        setItem(item)
      };
    const actionBodyTemplate = (rowData:any) => {
        return (
            <div className="flex align-items-center gap-2 ">
     {active &&

                <InputSwitch  className='ml-4' id={rowData.actionPermission.reference} checked={rowData.value as boolean}  onChange={(e) => handleSwitchChange(rowData, e.value)} />}

            </div>
        );
    };




return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("user.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("user.tabPan")}>
                <div className="formgrid grid">


                    <div className="field col-6">
                        <label htmlFor="email">{t("user.email")}</label>
                        <InputText id="email" value={item?.email} onChange={(e) => onInputTextChange(e, 'email')} required className={classNames({'p-invalid': submitted && !item.email})} />
                        {submitted && !item.email && <small className="p-invalid">Email is required.</small>}
                    </div>


                    <div className="field col-6">
                        <label htmlFor="username">{t("user.username")}</label>
                        <InputText id="username" value={item?.username} onChange={(e) => onInputTextChange(e, 'username')} required className={classNames({'p-invalid': submitted && !item.username})} />
                        {submitted && !item.username && <small className="p-invalid">Username is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="password">{t("user.password")}</label>
                        <InputText id="password" value={item?.password} onChange={(e) => onInputTextChange(e, 'password')} required className={classNames({'p-invalid': submitted && !item.password})} />
                        {submitted && !item.password && <small className="p-invalid">Password is required.</small>}
                    </div>

                    {/* <div className="field col-6">
                        <label htmlFor="lastName">{t("user.lastName")}</label>
                        <InputText id="lastName" value={item.lastName} onChange={(e) => onInputTextChange(e, 'lastName')} required className={classNames({'p-invalid': submitted && !item.lastName})} />
                        {submitted && !item.lastName && <small className="p-invalid">LastName is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="firstName">{t("user.firstName")}</label>
                        <InputText id="firstName" value={item.firstName} onChange={(e) => onInputTextChange(e, 'firstName')} required className={classNames({'p-invalid': submitted && !item.firstName})} />
                        {submitted && !item.firstName && <small className="p-invalid">FirstName is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="phone">{t("user.phone")}</label>
                        <InputText id="phone" value={item.phone} onChange={(e) => onInputTextChange(e, 'phone')} required className={classNames({'p-invalid': submitted && !item.phone})} />
                        {submitted && !item.phone && <small className="p-invalid">Phone is required.</small>}
                    </div> */}
                    {item?.roleUsers&&
                    <div className="field col-6">
                        <label htmlFor="roleUsers">{t("roleUser.role")}</label>
                        {roleUsers &&
                      <MultiSelect value={item?.roleUsers} options={roleUsers as any}  optionLabel="role.authority" display="chip" placeholder={t("user.roleUsersPlaceHolder")}  maxSelectedLabels={3}  onChange={(e) => onMultiSelectChange(e, 'roleUsers')} />
                    }

                    </div>
                    }
                    <div className="field col-6">
                    <div  className="label-inputswitch">
                        <label htmlFor="passwordChanged">{t("user.passwordChanged")}</label>
                        <span className="p-float-label">
                        <InputSwitch  id="passwordChanged" checked={item.passwordChanged as boolean} onChange={(e) => onBooleanInputChange(e, 'passwordChanged')} />
                        </span>
                    </div>
                    </div>
                    <div className="field col-6">
                    <div  className="label-inputswitch">
                        <label htmlFor="accountNonLocked">{t("user.accountNonLocked")}</label>
                        <span className="p-float-label">
                        <InputSwitch  id="accountNonLocked" checked={item.accountNonLocked as boolean} onChange={(e) => onBooleanInputChange(e, 'accountNonLocked')} />
                        </span>
                    </div>
                    </div>
                    <div className="field col-6">
                    <div  className="label-inputswitch">
                        <label htmlFor="accountNonExpired">{t("user.accountNonExpired")}</label>
                        <span className="p-float-label">
                        <InputSwitch  id="accountNonExpired" checked ={item.accountNonExpired as boolean} onChange={(e) => onBooleanInputChange(e, 'accountNonExpired')} />
                        </span>
                    </div>
                    </div>
                    <div className="field col-6">
                    <div  className="label-inputswitch">
                        <label htmlFor="enabled">{t("user.enabled")}</label>
                        <span className="p-float-label">
                        <InputSwitch  id="enabled" checked={item.enabled as boolean} onChange={(e) => onBooleanInputChange(e, 'enabled')} />
                        </span>
                    </div>
                    </div>
                    <div className="field col-6">
                    <div  className="label-inputswitch">
                        <label htmlFor="credentialsNonExpired">{t("user.credentialsNonExpired")}</label>
                        <span className="p-float-label">
                        <InputSwitch  id="credentialsNonExpired" checked={item.credentialsNonExpired as boolean} onChange={(e) => onBooleanInputChange(e, 'credentialsNonExpired')} />
                        </span>
                    </div>
                    </div>
                </div>
            </TabPanel>
            <TabPanel header={t("user.modelPermissionUsers")}>
            <div className="card">
{active ?(
            <DataTable value={modelPermissionUsers as any} rowGroupMode="subheader" groupRowsBy="modelPermission.reference"
                    sortMode="single" sortField="modelPermission.reference" sortOrder={1}
                    expandableRowGroups expandedRows={expandedRows} onRowToggle={(e: DataTableRowToggleEvent) => setExpandedRows(e.data as any)}
                    rowGroupHeaderTemplate={headerTemplate}  tableStyle={{ minWidth: '50rem' }}>
                <Column field="" header="Model Permission" style={{ width: '20%' }}>ssss</Column>
                {/* <Column field="country" header="Country" body={countryBodyTemplate} style={{ width: '20%' }}></Column> */}
                <Column field="actionPermission.reference" header="Action Permission" style={{ width: '20%' }} >yyzyzyyzsq</Column>
                {/* <Column field="status" header="Status" body={statusBodyTemplate} style={{ width: '20%' }}></Column> */}
                <Column field="date" header="Value" style={{ width: '20%' }} body={actionBodyTemplate}>ssss</Column>
            </DataTable>
):(<></>)
}
        </div>
            </TabPanel>
        </TabView>

    </Dialog>
);
};
export default Create;
