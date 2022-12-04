import axios from 'axios'

const API_REGISTER_URL = '/api/registration'
const API_LOGIN_URL = '/api/login'
const config = {
  headers: { Authorization: `Bearer ${localStorage.token}` }
}

class UserFrontService {
  registerNewUser (json) {
    return axios.post(API_REGISTER_URL, json)
  }

  loginUser (json) {
    return axios.post(API_LOGIN_URL, json)
  }

  getUser (email) {
    return axios.post(API_LOGIN_URL + '/getuser', email, config)
  }

  getUsers () {
    return axios.post(API_LOGIN_URL, config)
  }

  changeUserRole (json) {
    return axios.post(API_LOGIN_URL + '/role', json, config)
  }
}

export default new UserFrontService()