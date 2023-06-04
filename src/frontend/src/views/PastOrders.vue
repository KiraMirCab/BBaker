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
            <td>{{ prettyDate(order.creationDate, this.$i18n.locale) }}</td>
            <td v-if="(order.paidDate !== null)">{{ prettyDate(order.paidDate, this.$i18n.locale) }}</td>
            <td v-if="(order.paidDate === null)">{{ $t("product.notpaid") }}</td>
            <td>{{ order.total }}€</td>
            <td>
              <ul class="list-unstyled">
                <li v-for="product in order.orderProducts" :key="product.id">
                  <span v-if="this.$i18n.locale === 'es'">{{ product.product.name }} - {{ product.quantity }}</span>
                  <span v-else>{{ product.product.nameENG }} - {{ product.quantity }}</span>
                </li>
              </ul>
            </td>
            <td v-if="this.$i18n.locale === 'es'">{{ order.orderStatus.name }}</td>
            <td v-else> {{ this.order.orderStatus.nameENG }}</td>
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
      :toggle="toggle"
      :past="true"
    />

  </main>
</template>
<script>
import OrderFrontService from '@/services/OrderFrontService.js'
import DateTimeService from '@/services/DateTimeService'
import ExistingOrderVue from '@/components/ExistingOrder.vue'

export default {
  components: {
    ExistingOrderVue
  },
  data () {
    return {
      orders: [],
      order: '',
      orderVisible: false
    }
  },
  created () {
    this.getMyOrders()
  },
  methods: {
    getMyOrders () {
      OrderFrontService.getMyOrders(JSON.parse(localStorage.user).userID).then((response) => {
        this.orders = response.data
        this.order = response.data[0]
      })
    },
    prettyDate (timestamp, locale) {
      return DateTimeService.prettyDateShort(timestamp, locale)
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