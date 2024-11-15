import { ProgressSpinner } from 'primereact/progressspinner';
import React from 'react';

const loading = () => {
    return (
        <div className="flex flex-col justify-center items-center h-screen gap-4" style={{ alignItems: 'center' }}>
            <ProgressSpinner />
        </div>
    );
};

export default loading;
