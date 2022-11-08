import {backendApi} from "./backendApi";

const parcelClient = backendApi('/parcels')

export const parcelsApi = {
  getAll() {
    console.log('Fetching parcels')
    return parcelClient.get('')
  },

  getById(id) {
    console.log('Get parcel', id)
    return parcelClient.get(`/${id}`)
  },

  create(parcel) {
    console.log('Create parcel', parcel)
    return parcelClient.post('', parcel)
  },

  update(id, parcel) {
    console.log('Update parcel', id, parcel)
    return parcelClient.put(`/${id}`, parcel);
  },

  delete(id) {
    console.log('Delete parcel', id)
    return parcelClient.delete(`/${id}`)
  }
}
