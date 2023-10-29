import { backendApi } from './backendApi'

const noteClient = backendApi('/notes')

export const notesApi = {
  getAll () {
    console.log('Fetching notes')
    return noteClient.get('')
  },

  getById (id) {
    console.log('Get note', id)
    return noteClient.get(`/${id}`)
  },

  create (note) {
    console.log('Create note', note)
    return noteClient.post('', note)
  },

  update (id, note) {
    console.log('Update note', id, note)
    return noteClient.put(`/${id}`, note)
  },

  delete (id) {
    console.log('Delete note', id)
    return noteClient.delete(`/${id}`)
  }
}
