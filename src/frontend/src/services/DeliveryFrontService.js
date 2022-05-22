import axios from 'axios'

const API_PRODUCT_URL = '/api/delivery'

class DeliveryFrontService {
  getDeliveries () {
    return axios.get(API_PRODUCT_URL)
  }

  getDeliveryInfo (id) {
    return axios.get(API_PRODUCT_URL + '/' + id)
  }

  createNewDelivery (json) {
    return axios.post(API_PRODUCT_URL + '/add', json)
  }
}

export default new DeliveryFrontService()