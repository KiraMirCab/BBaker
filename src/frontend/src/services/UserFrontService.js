import axios from 'axios'

const API_REGISTER_URL = '/api/registration'

class UserFrontService {
  getUsers () {
    return axios.get(API_REGISTER_URL)
  }

  getUser (id) {
    return axios.get(API_REGISTER_URL + '/' + id)
  }

  registerNewUser (json) {
    return axios.post(API_REGISTER_URL, json)
  }
}

export default new UserFrontService()