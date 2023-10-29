import React from 'react'
import { Route, Routes } from 'react-router-dom'
import { NavBar } from './components/NavBar'
import { Home } from './pages/home/Home'
import { NotesPage } from './pages/notes/NotesPage'
import { NoteForm } from './pages/notes/form/NoteForm'

export default function App () {
  return (
    <>
      <NavBar />
      <Routes>
        <Route index element={<Home />} />
        <Route path='/notes' element={<NotesPage />} />
        <Route path='/notes/:noteId' element={<NoteForm />} />
      </Routes>
    </>
  )
}
