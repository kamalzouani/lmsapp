/* eslint-disable @next/next/no-img-element */

import Link from 'next/link';
import { classNames } from 'primereact/utils';
import React, { forwardRef, useContext, useImperativeHandle, useRef, useState } from 'react';
import { AppTopbarRef } from '@/types';
import { LayoutContext } from './context/layoutcontext';
import { AuthService } from '@/utils/zynerator/security/Auth.service';
import { useRouter } from 'next/navigation';
import { Dropdown } from 'primereact/dropdown';
import { useTranslation } from 'react-i18next';
// import { useTranslation } from 'react-i18next';

const AppTopbar = forwardRef<AppTopbarRef>((props, ref) => {
    const { layoutConfig, layoutState, onMenuToggle, showProfileSidebar } = useContext(LayoutContext);
    const menubuttonRef = useRef(null);
    const topbarmenuRef = useRef(null);
    const topbarmenubuttonRef = useRef(null);
    const router = useRouter();

    useImperativeHandle(ref, () => ({
        menubutton: menubuttonRef.current,
        topbarmenu: topbarmenuRef.current,
        topbarmenubutton: topbarmenubuttonRef.current
    }));
    const authService = new AuthService();
    const signOut = () => {
        authService.signOut();
        router.push('/auth/login');
    }
    const languages = ['en', 'fr', 'es','ar'];
    //appel de i18n qui la translation
    const { t, i18n } = useTranslation();
    const [showOptions, setShowOptions] = useState(false);
    const [selectedLanguage, setSelectedLanguage] = useState(i18n.language);

    const handleLanguageChange = (language: string) => {
        i18n.changeLanguage(language);
        setSelectedLanguage(language);
        setShowOptions(false);
    };

    return (
        <div className="layout-topbar">
            <Link href="/" className="layout-topbar-logo">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="w-2 h-2">
  <path fill-rule="evenodd" d="M14.615 1.595a.75.75 0 0 1 .359.852L12.982 9.75h7.268a.75.75 0 0 1 .548 1.262l-10.5 11.25a.75.75 0 0 1-1.272-.71l1.992-7.302H3.75a.75.75 0 0 1-.548-1.262l10.5-11.25a.75.75 0 0 1 .913-.143Z" clip-rule="evenodd" />
</svg>
                <span>ynerator</span>
            </Link>

            <button ref={menubuttonRef} type="button" className="p-link layout-menu-button layout-topbar-button" onClick={onMenuToggle}>
                <i className="pi pi-bars" />
            </button>

            <button ref={topbarmenubuttonRef} type="button" className="p-link layout-topbar-menu-button layout-topbar-button" onClick={showProfileSidebar}>
                <i className="pi pi-ellipsis-v" />
            </button>

            <div ref={topbarmenuRef} className={classNames('layout-topbar-menu', { 'layout-topbar-menu-mobile-active': layoutState.profileSidebarVisible })}>
            <div>
                    <Dropdown
                        value={selectedLanguage}
                        options={languages.map(lang => ({ label: t(lang), value: lang }))}
                        onChange={(e) => {
                            setSelectedLanguage(e.value);
                            handleLanguageChange(e.value);
                        }}
                        className=' p-dropdown rounded-md  text-lg   hover:border-bleu-300 focus:border-bleu-300  '
                        />
                </div>
                <Link href="/profile" type="button" className="p-link layout-topbar-button">
                    <i className="pi pi-user"></i>
                    <span>Profile</span>
                </Link>
                <button type='button' className='p-link layout-topbar-button' onClick={signOut}>
                    <i className='pi pi-sign-out'></i>
                    <span>Deconnexion</span>
                </button>
            </div>
        </div>
    );
});

AppTopbar.displayName = 'AppTopbar';

export default AppTopbar;
