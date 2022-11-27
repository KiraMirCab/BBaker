import axios from 'axios'

const API_REGISTER_URL = '/api/registration'
const API_LOGIN_URL = '/api/login'

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

  loginUser (json) {
    return axios.post(API_LOGIN_URL, json)
  }

  getUserRole (json) {
    return axios.post(API_LOGIN_URL + '/role', json)
  }
}

export default new UserFrontService()