<template>
  <GoogleMap
    :api-key="api"
    style="width: 100%; height: 93vh"
    :center="center"
    :zoom="14"
  >
    <Marker
        :key="index"
        v-for="(m, index) in markers"
        :options="{ position: m }"
        @click="center=m" />
  </GoogleMap>
</template>

<script>
import DeliveryFrontService from '@/services/DeliveryFrontService'
import { defineComponent } from 'vue'
import { GoogleMap, Marker } from 'vue3-google-map'

export default defineComponent({
  name: 'MapDeliveries',
  components: {
    GoogleMap,
    Marker
  },
  data () {
    return {
      api: process.env.VUE_APP_GOOGLE_API_KEY,
      center: { lat: 37.36250205559738, lng: -6.034784330451754 },
      deliveries: [],
      markers: []
    }
  },
  mounted () {
    this.geolocate()
    this.getDeliveries()
  },
  methods: {
    getDeliveries () {
      DeliveryFrontService.getReadytDeliveries().then((response) => {
        this.deliveries = response.data
        this.deliveries.forEach(element => {
          this.geocode(element.address)
        })
      })
    },
    geolocate () {
      navigator.geolocation.getCurrentPosition(position => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        }
      })
    },
    geocode (address) {
      DeliveryFrontService.geocodeAddress(address)
        .then((response) => {
          console.log(response)
          const results = response.data.results
          if (results.length > 0) {
            const location = results[0].geometry.location
            this.markers.push(location)
          }
        })
        .catch(error => {
          console.log('Error geocoding address: ', error.message)
        })
    }
  }
})
</script>