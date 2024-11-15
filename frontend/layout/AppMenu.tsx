/* eslint-disable @next/next/no-img-element */
"use client"
import { AppMenuItem } from '@/types';
import { MenuProvider } from './context/menucontext';
import AppMenuitem from './AppMenuitem';
import {AuthService} from '@/utils/zynerator/security/Auth.service';
import React, {useEffect, useState} from 'react';


const AppMenu = () => {

    const [model,setModel] = useState<AppMenuItem[]>([] as AppMenuItem[]);
    const authService = new AuthService();
        const modelAdmin: AppMenuItem[] = [
        {
            label: 'Home',
            items: [{label: 'Dashboard', icon: 'pi pi-fw pi-home', to: '/'}]
        },


        {
            label: 'Pages',
            icon: 'pi pi-fw pi-briefcase',
            to: '',
            items: [

                {
                    label: 'Auth',
                    icon: 'pi pi-fw pi-user',
                    items: [
                        {
                            label: 'Error',
                            icon: 'pi pi-fw pi-times-circle',
                            to: '/auth/error'
                        },
                        {
                            label: 'Access Denied',
                            icon: 'pi pi-fw pi-lock',
                            to: '/auth/access'
                        }
                    ]
                },
                {
                    label: 'Student Management',
                    icon: 'pi pi-fw pi-plus-circle',
                    items: [
                      {
                      label: 'Liste review',
                     to: '/admin/view/student/review'
                      },
                      {
                      label: 'Liste payment state',
                     to: '/admin/view/student/payment-state'
                      },
                      {
                      label: 'Liste student',
                     to: '/admin/view/student/student'
                      },
                      {
                      label: 'Liste enrollment state',
                     to: '/admin/view/student/enrollment-state'
                      },
                      {
                      label: 'Liste enrollment',
                     to: '/admin/view/student/enrollment'
                      },
                      {
                      label: 'Liste payment',
                     to: '/admin/view/student/payment'
                      },
                    ]
                    },
                {
                    label: 'Course Management',
                    icon: 'pi pi-fw pi-plus-circle',
                    items: [
                      {
                      label: 'Liste category',
                     to: '/admin/view/course/category'
                      },
                      {
                      label: 'Liste level',
                     to: '/admin/view/course/level'
                      },
                      {
                      label: 'Liste language',
                     to: '/admin/view/course/language'
                      },
                      {
                      label: 'Liste curriculum',
                     to: '/admin/view/course/curriculum'
                      },
                      {
                      label: 'Liste lesson',
                     to: '/admin/view/course/lesson'
                      },
                      {
                      label: 'Liste resource',
                     to: '/admin/view/course/resource'
                      },
                      {
                      label: 'Liste module',
                     to: '/admin/view/course/module'
                      },
                      {
                      label: 'Liste course',
                     to: '/admin/view/course/course'
                      },
                    ]
                    },
                {
                    label: 'Instructor Management',
                    icon: 'pi pi-fw pi-plus-circle',
                    items: [
                      {
                      label: 'Liste instructor',
                     to: '/admin/view/instructor/instructor'
                      },
                    ]
                    },
            ]
        },

		{
            label: 'Security Management',
            icon: 'pi pi-fw pi-plus-circle',
            items: [
                {
                    label: 'Liste role',
                    to: '/security/role'
                },
                //   {
                //   label: 'Liste model permission user',
                //  to: '/admin/view/security/model-permission-user'
                //   },
                {
                    label: 'Liste action permission',
                    to: '/security/action-permission'
                },
                {
                    label: 'Liste model permission',
                    to: '/security/model-permission'
                },
                {
                    label: 'Liste user',
                    to: '/security/user'
                },
            ]

        },

    ];
    const modelStudent: AppMenuItem[] = [
        {
            label: 'Home',
            items: [{label: 'Dashboard', icon: 'pi pi-fw pi-home', to: '/'}]
        },


        {
            label: 'Pages',
            icon: 'pi pi-fw pi-briefcase',
            to: '',
            items: [

                {
                    label: 'Auth',
                    icon: 'pi pi-fw pi-user',
                    items: [
                        {
                            label: 'Error',
                            icon: 'pi pi-fw pi-times-circle',
                            to: '/auth/error'
                        },
                        {
                            label: 'Access Denied',
                            icon: 'pi pi-fw pi-lock',
                            to: '/auth/access'
                        }
                    ]
                },
                {
                    label: 'Student Management',
                    icon: 'pi pi-fw pi-plus-circle',
                    items: [
                      {
                      label: 'Liste student',
                     to: '/student/view/student/student'
                      },
                    ]
                    },
            ]
        },

		{
            label: 'Security Management',
            icon: 'pi pi-fw pi-plus-circle',
            items: [
                {
                    label: 'Liste role',
                    to: '/security/role'
                },
                //   {
                //   label: 'Liste model permission user',
                //  to: '/admin/view/security/model-permission-user'
                //   },
                {
                    label: 'Liste action permission',
                    to: '/security/action-permission'
                },
                {
                    label: 'Liste model permission',
                    to: '/security/model-permission'
                },
                {
                    label: 'Liste user',
                    to: '/security/user'
                },
            ]

        },

    ];
    const modelInstructor: AppMenuItem[] = [
        {
            label: 'Home',
            items: [{label: 'Dashboard', icon: 'pi pi-fw pi-home', to: '/'}]
        },


        {
            label: 'Pages',
            icon: 'pi pi-fw pi-briefcase',
            to: '',
            items: [

                {
                    label: 'Auth',
                    icon: 'pi pi-fw pi-user',
                    items: [
                        {
                            label: 'Error',
                            icon: 'pi pi-fw pi-times-circle',
                            to: '/auth/error'
                        },
                        {
                            label: 'Access Denied',
                            icon: 'pi pi-fw pi-lock',
                            to: '/auth/access'
                        }
                    ]
                },
                {
                    label: 'Instructor Management',
                    icon: 'pi pi-fw pi-plus-circle',
                    items: [
                      {
                      label: 'Liste instructor',
                     to: '/instructor/view/instructor/instructor'
                      },
                    ]
                    },
            ]
        },

		{
            label: 'Security Management',
            icon: 'pi pi-fw pi-plus-circle',
            items: [
                {
                    label: 'Liste role',
                    to: '/security/role'
                },
                //   {
                //   label: 'Liste model permission user',
                //  to: '/admin/view/security/model-permission-user'
                //   },
                {
                    label: 'Liste action permission',
                    to: '/security/action-permission'
                },
                {
                    label: 'Liste model permission',
                    to: '/security/model-permission'
                },
                {
                    label: 'Liste user',
                    to: '/security/user'
                },
            ]

        },

    ];

    useEffect(()=>{
        const roleConnectedUser = authService.getRoleConnectedUser();
        if(roleConnectedUser === 'ROLE_ADMIN'){
            setModel(modelAdmin)
        }
        if(roleConnectedUser === 'ROLE_STUDENT'){
            setModel(modelStudent)
        }
        if(roleConnectedUser === 'ROLE_INSTRUCTOR'){
            setModel(modelInstructor)
        }
    },[])

    return (
        <MenuProvider>
            <ul className="layout-menu">
                {model.map((item, i) => {
                    return !item?.seperator ? <AppMenuitem item={item} root={true} index={i} key={item.label}/> :
                        <li className="menu-separator"></li>;
                })}


            </ul>
        </MenuProvider>
    );
};


export default AppMenu;
