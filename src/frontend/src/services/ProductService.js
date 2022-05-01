import axios from 'axios'

const API_PRODUCT_URL = '/api/products'

class ProductService {
  getProducts () {
    return axios.get(API_PRODUCT_URL)
  }

  getProductInfo (id) {
    return axios.get(API_PRODUCT_URL + '/' + id)
  }

  createNewProduct (json) {
    return axios.post(API_PRODUCT_URL + '/add', json)
  }
}

export default new ProductService()