import {useKeycloak} from "@react-keycloak/web";
import {Outlet} from "react-router-dom";

export const ProtectedRoute = () => {
  const {keycloak} = useKeycloak();

  return keycloak.authenticated ? <Outlet/> : keycloak.login();
};