import axios from 'axios'

const API_DELIVERY_URL = '/api/delivery'

class DeliveryFrontService {
  getDeliveries () {
    return axios.get(API_DELIVERY_URL)
  }

  getReadytDeliveries () {
    return axios.get(API_DELIVERY_URL + '/ready')
  }

  getDeliveryInfo (id) {
    return axios.get(API_DELIVERY_URL + '/' + id)
  }

  createNewDelivery (json) {
    return axios.post(API_DELIVERY_URL + '/add', json)
  }

  findDeliveryByOrderId (id) {
    return axios.get(API_DELIVERY_URL + '/order/' + id)
  }

  geocodeAddress (address) {
    const url = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${process.env.VUE_APP_GOOGLE_API_KEY}`
    return axios.get(url)
  }
}

export default new DeliveryFrontService()