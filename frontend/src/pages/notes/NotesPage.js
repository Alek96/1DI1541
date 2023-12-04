import React, { useEffect, useState } from 'react'
import { Button, ButtonGroup, Container, Table } from 'reactstrap'
import { Link } from 'react-router-dom'
import { notesApi } from '../../api/notesApi'
import { useAuth } from 'react-oidc-context'

export const NotesPage = () => {
  const auth = useAuth()
  const accessToken = auth.user.access_token
  const [notes, setNotes] = useState([])

  useEffect(() => {
    notesApi.getAll(accessToken)
      .then((res) => {
        setNotes(res.data)
      })
      .catch(err => console.error('[Fetch Error]:', err))
  }, [])

  const remove = (id) => {
    notesApi.delete(id, accessToken)
      .then(() => {
        setNotes((notes) => notes.filter((note) => note.id !== id))
      })
  }

  const noteList = notes.map(note => {
    return (
      <tr key={note.id}>
        <td style={{ whiteSpace: 'nowrap' }}>{note.id}</td>
        <td style={{ whiteSpace: 'nowrap' }}>{note.title}</td>
        <td style={{ whiteSpace: 'nowrap' }}>{note.text}</td>
        <td style={{ whiteSpace: 'nowrap' }}>{note.createdBy}</td>
        <td align='center'>
          <ButtonGroup>
            <Button size='sm' color='primary' tag={Link} to={'/notes/' + note.id}>
              Edit
            </Button>
            <Button size='sm' color='danger' onClick={() => remove(note.id)}>
              Delete
            </Button>
          </ButtonGroup>
        </td>
      </tr>
    )
  })

  return (
    <div>
      <Container fluid>
        <h3>Notes</h3>
        <Table striped bordered hover size='sm'>
          <thead>
            <tr>
              <th width='80px'>Id</th>
              <th>Title</th>
              <th>Text</th>
              <th>Created By</th>
              <th width='120px'>
                <div align='center'>Action</div>
              </th>
            </tr>
          </thead>
          <tbody>
            {noteList}
          </tbody>
        </Table>
        <div className='float-right'>
          <Button color='success' tag={Link} to='/notes/new'>
            Add
          </Button>
        </div>
      </Container>
    </div>
  )
}
