import React from "react";
import {Nav, Navbar, NavbarBrand} from "reactstrap";
import {NavLink} from "react-router-dom";

export const NavBar = () => {
  const style = ({isActive}) => ({
    fontWeight: isActive ? 'bold' : 'normal',
  });

  return (
      <Navbar color="dark" dark expand="md">
        <Nav>
          <NavbarBrand tag={NavLink} to="/" style={style}>Home</NavbarBrand>
          <NavbarBrand tag={NavLink} to="/parcels" style={style}>Parcels</NavbarBrand>
        </Nav>
      </Navbar>
  );
}
