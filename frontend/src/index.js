import React, {StrictMode} from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter} from "react-router-dom";
import App from './App';
import reportWebVitals from './reportWebVitals';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {ReactKeycloakProvider} from "@react-keycloak/web";
import {keycloak} from "./components/Keycloak";
import {Loading} from "./components/Loading";

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <ReactKeycloakProvider
        authClient={keycloak}
        LoadingComponent={Loading}
    >
      <StrictMode>
        <BrowserRouter>
          <App/>
        </BrowserRouter>
      </StrictMode>
    </ReactKeycloakProvider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
