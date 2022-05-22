<template>
  <main class="wrapper">
    <h1>{{ $t("newOrder.header") }}{{ id }}</h1>

    <OrderInfo :orderProducts="orderProducts" />

    <div v-if="!showDeliveryForm" class="row">
      <div class="col-md">
        <button @click="confirmOrder()" class="btn btn-light">
          {{ $t("BBorder.confirm") }}
        </button></div>
      <div class="col-md">
        <button @click="deleteOrder()" class="btn btn-light">
          {{ $t("BBorder.cancel") }}
        </button></div>
    </div>

    <DeliveryForm
      v-if="showDeliveryForm"
      :id="id"
      :specialTransport="specialTransport"
      @confirm="confirm"
    />

    <button
      @click="pay()"
      class="btn btn-light"
      v-if="deliverySaved"
      >
        {{ $t("BBorder.pay") }}
    </button>
  </main>
</template>

<script>
import OrderInfo from '@/components/OrderInfo.vue'
import DeliveryForm from '@/components/DeliveryForm.vue'
import OrderFrontService from '@/services/OrderFrontService.js'
import router from '@/router'

export default {
  name: 'NewOrder',
  props: [],
  components: {
    OrderInfo,
    DeliveryForm
  },
  created () {
    this.id = this.$route.path.split('/')[2]
    console.log(this.id)
    this.getOrderInfo(this.id)
    console.log(this.orderProducts)
  },
  data () {
    return {
      id: '',
      orderProducts: [],
      specialTransport: false,
      showDeliveryForm: false,
      deliverySaved: false
    }
  },
  methods: {
    // extraer la iformación sobre el pedido y los productos incluidos de la bd
    getOrderInfo (id) {
      OrderFrontService.getOrderProducts(id).then((response) => {
        this.orderProducts = response.data
        // si alguno de los productos necesita transporte especial se apunta en la variable
        this.orderProducts.forEach(item => {
          if (item.product.specialTransport) {
            this.specialTransport = true
          }
        })
      })
    },
    confirmOrder () {
      this.showDeliveryForm = true
    },
    deleteOrder () {
      // TODO Mensaje de confirmación
      const json = { order_id: this.id }
      OrderFrontService.deleteOrder(json)
      // TODO mensaje de completarse la accion
      // TODO arreglar el router
      this.router.push('Home')
    },
    pay () {
      // TODO Mensaje de confirmación
      const json = {
        order_id: this.id,
        paidDate: Date.now()
      }
      OrderFrontService.setPaid(json)
      // TODO mensaje de completarse la accion
      router.push('Home')
    },
    confirm () {
      this.deliverySaved = true
    }
  }
}
</script>