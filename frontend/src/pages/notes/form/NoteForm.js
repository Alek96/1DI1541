import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap'
import { notesApi } from '../../../api/notesApi'

export const NoteForm = () => {
  const navigate = useNavigate()
  const { noteId } = useParams()
  const [note, setNote] = useState({
    title: '',
    text: ''
  })

  useEffect(() => {
    if (noteId !== 'new') {
      notesApi.getById(noteId)
        .then((res) => {
          setNote(res.data)
        })
    }
  }, [noteId])

  const handleChange = (event) => {
    const target = event.target
    const value = target.value
    const name = target.name

    setNote({ ...note, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault()

    if (note.id) {
      await notesApi.update(note.id, note)
    } else {
      await notesApi.create(note)
    }
    navigate('/notes')
  }

  const title = <h2>{note.id ? 'Edit note' : 'Add Note'}</h2>

  return (
    <Container>
      {title}
      <Form onSubmit={handleSubmit}>
        <FormGroup>
          <Label for='title'>Title</Label>
          <Input
            id='title'
            name='title'
            type='text'
            value={note.title || ''}
            onChange={handleChange}
            autoComplete='Title'
          />
        </FormGroup>
        <FormGroup>
          <Label for='text'>Text</Label>
          <Input
            id='text'
            name='text'
            type='text'
            value={note.text || ''}
            onChange={handleChange}
            autoComplete='Text'
          />
        </FormGroup>
        <FormGroup>
          <Button color='primary' type='submit'>Save</Button>{' '}
          <Button color='secondary' tag={Link} to='/notes'>Cancel</Button>
        </FormGroup>
      </Form>
    </Container>
  )
}
