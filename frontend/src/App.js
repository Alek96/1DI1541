import React from 'react';
import {Route, Routes} from 'react-router-dom';
import {NavBar} from "./components/NavBar";
import {Home} from "./pages/home/Home";
import {ParcelPage} from "./pages/parcels/ParcelPage";
import {ParcelForm} from "./pages/parcel/ParcelForm";

export default function App() {
  return (
      <>
        <NavBar/>
        <Routes>
          <Route index element={<Home/>}/>
          <Route path='/parcels' element={<ParcelPage/>}/>
          <Route path='/parcels/:parcelId' element={<ParcelForm/>}/>
        </Routes>
      </>
  )
}
