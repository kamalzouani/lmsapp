import axios, {AxiosResponse} from "axios";
import {RoleDto} from "@/utils/zynerator/security/model/Role.model";
import { ROLES_URL } from "@/layout/AppConfig";


class RoleService{

    getList(): Promise<AxiosResponse<RoleDto[]>> {
        return axios.get(ROLES_URL);
    }

}

export default RoleService;
