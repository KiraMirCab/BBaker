<template>
    <main class="wrapper">
      <h1>{{ $t("ordersNow.header") }}</h1>
      <br>
      <br>
      <div>
        <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Creation date</th>
      <th scope="col">Paid date</th>
      <th scope="col">Total price</th>
      <th scope="col">Customer</th>
      <th scope="col">Products and quantities</th>
      <th scope="col">Date of delivery</th>
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
      </td>
      <td v-if="order.delivery != null">{{ prettyDateFromDate(order.delivery.date) }}</td>
      <td v-if="order.delivery == null"> </td>
      <td>{{ order.orderStatus.name }}</td>

      <!-- Status change dropdown -->
      <td>
        <div class="dropdown">
          <button
            class="btn btn-secondary dropdown-toggle"
            type="button"
            id="dropdown1"
            data-bs-toggle="dropdown"
            aria-expanded="false"
            @click="setNewStatus()">
              Change status
          </button>
          <ul class="dropdown-menu" aria-labelledby="dropdown1">
            <li v-for="status in statuses" :key="status.id">
              <a class="dropdown-item" href="#" @click="selectStatus(status, order.id)">{{ status.name }}</a>
            </li>
          </ul>
        </div>
      </td>

      <!-- Delivery details button -->
      <td v-if="order.delivery != null">
        <button
          class="btn btn-primary"
          :data-bs-target="'#collapseTarget' + order.id"
          data-bs-toggle="collapse">
            Show
        </button>
        <div class="collapse py-2" :id="'collapseTarget' + order.id">{{ order.delivery }}</div>
      </td>
      <td v-if="order.delivery == null"> </td>

    </tr>
  </tbody>
</table>
</div>
</main>
</template>

<script>
import OrderFrontService from '@/services/OrderFrontService'
import DateTimeService from '@/services/DateTimeService'
import DeliveryFrontService from '@/services/DeliveryFrontService'
import Swal from 'sweetalert2'

export default {
  components: {

  },
  data () {
    return {
      orders: [],
      delivery: {},
      statuses: []
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
    findDeliveryByOrderId (id) {
      DeliveryFrontService.findDeliveryByOrderId(id)
        .then((response) => {
          this.delivery = response.data
        })
        .catch(error => {
          console.log(error.response.data)
        })
    },
    getOrderStatuses () {
      OrderFrontService.getStatuses()
        .then((response) => {
          this.statuses = response.data
        })
        .catch(error => {
          console.log(error.response.data)
        })
    },
    selectStatus (status, orderId) {
      Swal.fire({
        title: 'Are you sure?',
        text: 'The order will be set to "' + status.name + '" status',
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, change the status!'
      }).then((result) => {
        if (result.isConfirmed) {
          const json = {
            order_id: orderId,
            status_id: status.id
          }
          OrderFrontService.changeOrderStatus(json)
            .catch(error => {
              console.log(error.response.data)
            })
          Swal.fire(
            'Status changed!',
            'The status has been set to ' + status.name,
            'success'
          )
          this.$router.go(0)
        }
      })
    },
    prettyDate (timestamp, locale) {
      return DateTimeService.prettyDateShort(timestamp, locale)
    },
    prettyDateFromDate (date, locale) {
      return DateTimeService.prettyDateFromDate(date, locale)
    }
  },
  created () {
    this.getAllOrders()
    this.getOrderStatuses()
  }
}
</script>