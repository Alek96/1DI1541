import { Spinner } from 'reactstrap'
import React from 'react'

export const Loading = (
  <div
    style={{
      position: 'fixed',
      top: '50%',
      left: '50%',
      /* bring your own prefixes */
      transform: 'translate(-50%, -50%)'
    }}
  >
    <h1>Loading...
    </h1>
    <center>
      <Spinner style={{ width: '8rem', height: '8rem' }} />
    </center>
  </div>
)
