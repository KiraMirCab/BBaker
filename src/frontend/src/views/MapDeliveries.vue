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
      markers: []
    }
  },
  mounted () {
    this.geolocate()
    DeliveryFrontService.geocodeAddress('C/Puerto del Escudo 3, 9D Sevilla')
  },
  methods: {
    getDeliveries () {
      DeliveryFrontService.getDeliveries().then((response) => {
        const deliveries = response.data
        deliveries.forEach(element => {
          this.markers.push(element.address)
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
      this.markers = [
        {
          lat: 37.3759826,
          lng: -5.954058499999999,
          label: 'Rochela'
        }
      ]
    }
  },
  created () {
    // this.getDeliveries()
  }
})
</script>