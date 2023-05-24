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

  findDeliveryByOrderId (id) {
    return axios.get(API_PRODUCT_URL + '/order/' + id)
  }

  async geocodeAddress (address) {
    const url = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${process.env.VUE_APP_GOOGLE_API_KEY}`

    try {
      const response = await axios.get(url)
      const results = response.data.results

      if (results.length > 0) {
        const location = results[0].geometry.location
        const latitude = location.lat
        const longitude = location.lng

        // TODO: Añadir las coordinadas a la base de datos
        console.log(latitude)
        console.log(longitude)
      }
    } catch (error) {
      console.error('Error geocoding address:', error.message)
    }
  }
}

export default new DeliveryFrontService()