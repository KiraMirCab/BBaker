import axios from 'axios'

const API_ORDER_URL = '/api/BBorder'

class OrderService {
  getOrders () {
    return axios.get(API_ORDER_URL + '/')
  }

  getOrderInfo (id) {
    return axios.get(API_ORDER_URL + '/' + id)
  }

  getOrderProducts (id) {
    return axios.get(API_ORDER_URL + '/products/' + id)
  }

  getMyOrders (id) {
    return axios.get(API_ORDER_URL + '/myorders/' + id)
  }

  createNewOrder (json) {
    return axios.post(API_ORDER_URL + '/add', json)
  }

  setPaid (json) {
    return axios.post(API_ORDER_URL + '/paid', json)
  }

  deleteOrder (json) {
    return axios.post(API_ORDER_URL + '/delete', json)
  }

  getStatuses () {
    return axios.get(API_ORDER_URL + '/all_statuses')
  }

  changeOrderStatus (json) {
    return axios.post(API_ORDER_URL + '/change_status', json)
  }

  getStatusChanges (json) {
    console.log(json)
    return axios.post(API_ORDER_URL + '/status_history', json, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
  }
}

export default new OrderService()