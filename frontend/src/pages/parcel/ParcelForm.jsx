import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import {parcelsApi} from "../../api/parcelsApi";
import {useKeycloak} from "@react-keycloak/web";

export const ParcelForm = () => {
  const {keycloak} = useKeycloak();
  const navigate = useNavigate();
  const {parcelId} = useParams();
  const [parcel, setParcel] = useState({
    name: ''
  });

  useEffect(() => {
    if (parcelId !== 'new') {
      parcelsApi.getById(parcelId, keycloak.token)
      .then((res) => {
        setParcel(res.data);
      });
    }
    keycloak.updateToken()
  }, [parcelId]);

  const handleChange = (event) => {
    const target = event.target;
    const value = target.value;
    const name = target.name;

    setParcel({...parcel, [name]: value});
  }

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (parcel.id) {
      await parcelsApi.update(parcel.id, parcel, keycloak.token)
    } else {
      await parcelsApi.create(parcel, keycloak.token)
    }
    navigate('/parcels')
  }

  const title = <h2>{parcel.id ? 'Edit parcel' : 'Add Parcel'}</h2>;

  return (
      <Container>
        {title}
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input
                id="name"
                name="name"
                type="text"
                value={parcel.name || ''}
                onChange={handleChange}
                autoComplete="name"/>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/parcels">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
  )
}
