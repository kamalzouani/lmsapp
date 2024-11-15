import { AxiosResponse } from "axios";
import axios from 'axios';
import { ADMIN_URL, API_URL } from "@/layout/AppConfig";
import AbstractService from "../service/AbstractService";
import { UserCriteria } from "../criteria/UserCriteria.model";
import {UserDto} from "@/utils/zynerator/security/model/User.model";

export class UserService extends AbstractService<UserDto, UserCriteria>{

    constructor() {
        super(API_URL, 'user/');
    }

    getDashboardKPI(): Promise<AxiosResponse<any>> {
        return axios.get(`${ADMIN_URL}dashboard/utilisateur`);
    }

};
