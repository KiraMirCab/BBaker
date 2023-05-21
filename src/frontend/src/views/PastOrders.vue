<template>
  <main class="wrapper">
    <h1 class="center">{{ $t("menu.past") }}</h1>
    <div class="container" v-if="!orderVisible">
      <table class="table table-striped">
        <thead class="center">
          <th>#</th>
          <th>{{ $t("product.creationDate") }}</th>
          <th>{{ $t("product.paid") }}</th>
          <th>{{ $t("product.total") }}</th>
          <th>{{ $t("product.products") }}</th>
          <th>Status</th>
          <th> </th>
        </thead>
        <tbody class="center">
          <tr v-for="(order, i) in orders" :key="i">
            <td>{{ i+1 }}</td>
            <td>{{ prettyDate(order.creationDate) }}</td>
            <td v-if="(order.paidDate !== null)">{{ prettyDate(order.paidDate) }}</td>
            <td v-if="(order.paidDate === null)">{{ $t("product.notpaid") }}</td>
            <td>{{ order.total }}€</td>
            <td>
              <ul class="list-unstyled">
                <li v-for="product in order.orderProducts" :key="product.id">{{ getName(product.product.id) }} - {{ product.quantity }}</li>
              </ul>
            </td>
            <td v-if="this.$i18n.locale === 'es'">{{ order.orderStatus.name }}</td>
            <td v-else> {{ this.order.orderStatus.nameENG }}</td>
              <!-- <td>
                <table class="table">
                  <tr v-for="(orderProduct, index) in order.orderProducts" :key="index">
                    <td>{{ getName(orderProduct[index].product.id) }}</td>
                    <td>{{ $t("card.price") }}: {{ orderProduct[index].price}}€</td>
                    <td>{{ $t("card.quantity") }}: {{ orderProduct[index].quantity }}</td>
                  </tr>
                </table>
              </td> -->
            <td class="center">
              <button @click="viewOrder(order)" class="btn btn-light">
                {{ $t("card.details") }}
              </button>
            </td>
          </tr>
          <tr>
          </tr>
        </tbody>
      </table>
    </div>

    <ExistingOrderVue
      v-if="orderVisible"
      :order="order"
      :inventory="inventory"
      :toggle="toggle"
      :past="true"
    />

  </main>
</template>
<script>
import OrderFrontService from '@/services/OrderFrontService.js'
import ProductService from '@/services/ProductService.js'
import ExistingOrderVue from '@/components/ExistingOrder.vue'

export default {
  components: {
    ExistingOrderVue
  },
  data () {
    return {
      orders: [],
      inventory: [],
      order: '',
      orderVisible: false
    }
  },
  created () {
    this.getMyOrders()
    this.getAllProducts()
  },
  methods: {
    getMyOrders () {
      OrderFrontService.getMyOrders(localStorage.user_id).then((response) => {
        this.orders = response.data
        this.order = response.data[0]
      })
    },
    getName (id) {
      const product = this.inventory.find(product => product.id === id)
      if (this.$i18n.locale === 'es') {
        return product.name
      } else {
        return product.nameENG
      }
    },
    getAllProducts () {
      ProductService.getProducts().then((response) => {
        this.inventory = response.data
      })
    },
    prettyDate (timestamp) {
      const newDate = new Date(timestamp)
      const options = { year: 'numeric', month: 'long', day: 'numeric' }
      if (this.$i18n.locale === 'es') {
        return newDate.toLocaleDateString('es-ES', options) + '  ' + newDate.toLocaleTimeString('es-ES', { hour: '2-digit', minute: '2-digit' })
      } else {
        return newDate.toLocaleDateString('en-EN', options) + '  ' + newDate.toLocaleTimeString('en-EN', { hour: '2-digit', minute: '2-digit' })
      }
    },
    viewOrder (order) {
      this.order = order
      console.log(order)
      this.toggle()
    },
    toggle () {
      this.orderVisible = !this.orderVisible
    }
  }
}
</script>