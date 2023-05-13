<template>
  <main class="wrapper">
    <h1>{{ $t("newOrder.header") }}{{ id }}</h1>
    <div id="confirm"></div>

    <OrderInfo
      :order="order"
      :orderProducts="orderProducts"
      />

    <div v-if="!showDeliveryForm" class="row">
      <div class="col-md">
        <button @click="confirmOrder()" class="btn btn-light">
          {{ $t("BBorder.confirm") }}
        </button>
      </div>
      <div class="col-md">
        <button @click="deleteOrder()" class="btn btn-light">
          {{ $t("BBorder.cancel") }}
        </button>
      </div>
    </div>

    <DeliveryForm v-if="showDeliveryForm"
      :id="id"
      :specialTransport="specialTransport"
      :toggle="togglePayButton"
     />

    <button @click="pay()" class="btn btn-light" v-if="deliverySaved">
      {{ $t("BBorder.pay") }}
    </button>
  </main>
</template>

<script>
import OrderInfo from '@/components/OrderInfo.vue'
import DeliveryForm from '@/components/DeliveryForm.vue'
import OrderFrontService from '@/services/OrderFrontService.js'
import Swal from 'sweetalert2'

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
      order: {},
      specialTransport: false,
      showDeliveryForm: false,
      deliverySaved: false
    }
  },
  methods: {
    // extraer la iformación sobre el pedido y los productos incluidos de la bd
    getOrderInfo (id) {
      OrderFrontService.getOrderInfo(id).then((response) => {
        this.order = response.data
        this.orderProducts = this.order.orderProducts
        // si alguno de los productos necesita transporte especial se apunta en la variable
        this.orderProducts.forEach(item => {
          if (item.product.specialTransport) {
            this.specialTransport = true
          }
        })
      })
    },
    confirmOrder () {
      Swal.fire('Now let\'s check your address')
      this.showDeliveryForm = true
    },
    deleteOrder () {
      // TODO Mensaje de confirmación
      Swal.fire({
        title: 'Are you sure?',
        text: "Your order will be cancelled and you won't get these yum-yums!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
          const json = { order_id: this.id }
          OrderFrontService.deleteOrder(json)
          Swal.fire(
            'Deleted!',
            'Your order was cancelled',
            'success'
          )
          this.$router.go(-1)
        }
      })
    },
    togglePayButton () {
      this.deliverySaved = !this.deliverySaved
    },
    pay () {
      const json = {
        order_id: this.id,
        paidDate: Date.now()
      }
      console.log(json)
      OrderFrontService.setPaid(json)
      Swal.fire({
        icon: 'success',
        title: 'Thank you!',
        text: 'The payment was successfull',
        showConfirmButton: false,
        timer: 1500
      })
      this.$router.go(-1)
    }
  }
}
</script>