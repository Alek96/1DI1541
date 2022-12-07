import React from "react";
import {Nav, Navbar, NavbarBrand} from "reactstrap";
import {NavLink, useNavigate} from "react-router-dom";
import {useKeycloak} from "@react-keycloak/web";

export const NavBar = () => {
  const {keycloak} = useKeycloak();
  const navigate = useNavigate();

  const style = ({isActive}) => ({
    fontWeight: isActive ? 'bold' : 'normal',
  });

  const handleLogIn = () => {
    keycloak.login()
  }

  const handleLogOut = () => {
    navigate('/')
    keycloak.logout()
  }

  return (
      <Navbar color="dark" dark expand="md">
        <Nav>
          <NavbarBrand tag={NavLink} to="/" style={style}>Home</NavbarBrand>
          <NavbarBrand tag={NavLink} to="/parcels" style={style}>Parcels</NavbarBrand>
          {!keycloak.authenticated && (
              <NavbarBrand tag={NavLink} to="/login" onClick={handleLogIn}>
                Login
              </NavbarBrand>
          )}
          {keycloak.authenticated && (
              <NavbarBrand tag={NavLink} to="/Logout" onClick={handleLogOut} style={{right:0}}>
                Logout
              </NavbarBrand>
          )}
        </Nav>
      </Navbar>
  );
}
