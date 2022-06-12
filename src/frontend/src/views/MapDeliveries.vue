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
          lat: 21.1594627,
          lng: 72.6822083,
          label: 'Surat'
        },
        {
          lat: 23.0204978,
          lng: 72.4396548,
          label: 'Ahmedabad'
        },
        {
          lat: 22.2736308,
          lng: 70.7512555,
          label: 'Rajkot'
        }
      ]
    }
  },
  created () {
    // this.getDeliveries()
  }
})
</script>