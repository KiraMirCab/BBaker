<template>
    <main class="wrapper">
      <h1>{{ $t("ordersNow.header") }}</h1>
      <div class="container">
        <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Creation date</th>
      <th scope="col">Paid date</th>
      <th scope="col">Total price</th>
      <th scope="col">Customer</th>
      <th scope="col">Products and quantities</th>
      <th scope="col">Due date</th>
      <th scope="col">Current status</th>
      <th scope="col">Change status</th>
      <th scope="col">Delivery details</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(order, index) in orders" :key="order.id">
      <th scope="row">{{ index + 1 }}</th>
      <td>{{ prettyDate(order.creationDate, this.$i18n.locale) }}</td>
      <td>{{ prettyDate(order.paidDate, this.$i18n.locale) }}</td>
      <td>{{ order.total }} €</td>
      <td v-if="order.user != null">{{ order.user.firstName }} {{ order.user.lastName }}</td>
      <td v-if="order.user == null"> </td>
      <td>
        <ul class="list-unstyled">
          <li v-for="product in order.orderProducts" :key="product.id">{{ product.product.name }} - {{ product.quantity }}</li>
        </ul>
        <!-- <div> -->
          <!-- <label><pre>{{getProducts(order.orderProducts)}}</pre></label> -->
        <!-- </div> -->
      </td>
      <td></td>
      <td></td>
      <td></td>
      <td>
        <button
          class="btn btn-primary"
          data-bs-target="#collapseTarget"
          data-bs-toggle="collapse">
          Show
        </button>
        <div class="collapse py-2" id="collapseTarget">Delivery details: </div>
      </td>
    </tr>
  </tbody>
</table>

      </div>
    </main>
  </template>

<script>
import OrderFrontService from '@/services/OrderFrontService'
import DateTimeService from '@/services/DateTimeService'

export default {
  components: {

  },
  data () {
    return {
      orders: []
    }
  },
  methods: {
    getAllOrders () {
      OrderFrontService.getOrders()
        .then((response) => {
          this.orders = response.data
        })
        .catch(error => {
          console.log(error.response.data)
        })
    },
    getProducts (orderProducts) {
      return orderProducts.map((orderProduct) => {
        const pName = orderProduct.product.name
        const pQuantity = orderProduct.quantity
        return `${pName} - ${pQuantity}`
      }).join('\n')
    },
    prettyDate (timestamp, locale) {
      return DateTimeService.prettyDateShort(timestamp, locale)
    }
  },
  created () {
    this.getAllOrders()
  }
}
</script>