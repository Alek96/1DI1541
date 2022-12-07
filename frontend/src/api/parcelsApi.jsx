import {backendApi} from "./backendApi";
import {bearerAuth} from "./bearerAuth";

const parcelClient = backendApi('/parcels')

export const parcelsApi = {
  getAll(token) {
    console.log('Fetching parcels')
    return parcelClient.get('', {
      headers: {'Authorization': bearerAuth(token)}
    })
  },

  getById(id, token) {
    console.log('Get parcel', id)
    return parcelClient.get(`/${id}`, {
      headers: {'Authorization': bearerAuth(token)}
    })
  },

  create(parcel, token) {
    console.log('Create parcel', parcel)
    return parcelClient.post('', parcel, {
      headers: {'Authorization': bearerAuth(token)}
    })
  },

  update(id, parcel, token) {
    console.log('Update parcel', id, parcel)
    return parcelClient.put(`/${id}`, parcel, {
      headers: {'Authorization': bearerAuth(token)}
    })
  },

  delete(id, token) {
    console.log('Delete parcel', id)
    return parcelClient.delete(`/${id}`, {
      headers: {'Authorization': bearerAuth(token)}
    })
  }
}
