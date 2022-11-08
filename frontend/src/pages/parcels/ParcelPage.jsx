import React, {useEffect, useState} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import {parcelsApi} from "../../api/parcelsApi";

export const ParcelPage = () => {
  const [parcels, setParcels] = useState([]);

  useEffect(() => {
    parcelsApi.getAll()
    .then((res) => {
      setParcels(res.data);
    })
  }, []);

  const remove = (id) => {
    parcelsApi.delete(id)
    .then(() => {
      setParcels((parcels) => parcels.filter((parcel) => parcel.id !== id));
    });
  }

  const parcelList = parcels.map(parcel => {
    return <tr key={parcel.id}>
      <td style={{whiteSpace: 'nowrap'}}>{parcel.id}</td>
      <td style={{whiteSpace: 'nowrap'}}>{parcel.name}</td>
      <td align="center">
        <ButtonGroup>
          <Button size="sm" color="primary" tag={Link} to={"/parcels/" + parcel.id}>
            Edit
          </Button>
          <Button size="sm" color="danger" onClick={() => remove(parcel.id)}>
            Delete
          </Button>
        </ButtonGroup>
      </td>
    </tr>
  });

  return (
      <div>
        <Container fluid>
          <h3>Parcels</h3>
          <Table striped bordered hover size="sm">
            <thead>
            <tr>
              <th width="80px">Id</th>
              <th>Name</th>
              <th width="120px">
                <div align="center">Action</div>
              </th>
            </tr>
            </thead>
            <tbody>
            {parcelList}
            </tbody>
          </Table>
          <div className="float-right">
            <Button color="success" tag={Link} to="/parcels/new">
              Add
            </Button>
          </div>
        </Container>
      </div>
  );
}
