import React, { StrictMode } from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter } from 'react-router-dom'
import App from './App'
import reportWebVitals from './reportWebVitals'
import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import { AuthProvider } from 'react-oidc-context'
import { OidcConfig } from './components/OidcConfig'

const root = ReactDOM.createRoot(document.getElementById('root'))

root.render(
  <AuthProvider {...OidcConfig}>
    <StrictMode>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </StrictMode>
  </AuthProvider>
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals()
