<template>
  <main class="wrapper">
    <h1 v-if="!orderVisible">{{ $t("ordersNow.header") }}</h1>
    <div v-if="!orderVisible">
      <br>
      <div class="row">

        <!-- Dropdown para filtrar los pedidos por estado -->
        <div class="col">
          <div class="dropdown">
            <button
            class="btn btn-secondary dropdown-toggle"
            type="button"
            id="dropdownFilter"
            data-bs-toggle="dropdown"
            aria-expanded="false">
            Filter by status
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownFilter">
              <li v-for="status in statuses" :key="status.id">
                <a class="dropdown-item" href="#" @click="filterByStatus(status)">{{ status.name }}</a>
              </li>
            </ul>
          </div>
        </div>

        <!-- Datepicker para filtrar los pedidos por fecha de la entrega-->
        <div class="col-6">
          <div class="row">
            <div class="col">
              <label for="date">{{ $t('delivery.date') }}:</label>
            </div>
            <div class="col">
              <datepicker
              v-model="deliveryDate"
              :lower-limit="availableFrom"
              id="date"
              />
            </div>
            <div class="col">
              <button @click="filterByDeliveryDate(deliveryDate)" class="btn btn-secondary"> Filter </button>
            </div>
          </div>
        </div>

        <!-- Botón para limpiar la búsqueda -->
        <div class="col">
          <button @click="clearFilter()" class="btn btn-secondary">Clear filter</button>
        </div>
      </div>
      <br>

      <!-- Tabla de los pedidos -->
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
            <th scope="col">Delivery</th>
            <th scope="col">Current status</th>
            <th scope="col">Change status</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(orderDelivery, index) in orderDeliveriesToShow" :key="orderDelivery.order.id">
              <th scope="row">{{ index + 1 }}</th>
              <td>{{ prettyDate(orderDelivery.order.creationDate, this.$i18n.locale) }}</td>
              <td>{{ prettyDate(orderDelivery.order.paidDate, this.$i18n.locale) }}</td>
              <td>{{ orderDelivery.order.total }} €</td>
              <td v-if="orderDelivery.order.user != null">{{ orderDelivery.order.user.firstName }} {{ orderDelivery.order.user.lastName }}</td>
              <td v-if="orderDelivery.order.user == null"> </td>
              <td>
                <ul class="list-unstyled">
                  <li v-for="product in orderDelivery.order.orderProducts" :key="product.id">
                    <span v-if="this.$i18n.locale === 'es'">{{ product.product.name }} - {{ product.quantity }}</span>
                    <span v-else>{{ product.product.nameENG }} - {{ product.quantity }}</span>
                  </li>
                </ul>
              </td>
              <td v-if="orderDelivery.date != null">{{ prettyDateFromDate(orderDelivery.date) }}</td>
              <td v-if="orderDelivery.date == null"> </td>
              <td>
                <i v-if="orderDelivery.surprise" class="icofont-gift icofont-2x"></i>
                <i v-if="orderDelivery.specialTransport" class="icofont-truck-loaded icofont-2x"></i>
              </td>
              <td>{{ orderDelivery.order.orderStatus.name }}</td>
              <!-- Status change dropdown -->
              <td>
                <div class="dropdown">
                  <button
                  class="btn btn-secondary dropdown-toggle"
                  type="button"
                  id="dropdown1"
                    data-bs-toggle="dropdown"
                    aria-expanded="false">
                      Change status
                  </button>
                  <ul class="dropdown-menu" aria-labelledby="dropdown1">
                    <li v-for="status in statuses" :key="status.id">
                      <a class="dropdown-item" href="#" @click="selectStatus(status, orderDelivery.order.id)">{{ status.name }}</a>
                    </li>
                  </ul>
                </div>
              </td>
            <td class="center">
              <button @click="viewOrder(orderDelivery)" class="btn btn-light">
                {{ $t("card.details") }}
              </button>
            </td>
        </tr>
      </tbody>
    </table>
  </div>

  <!-- Esta sección es visible solamente cuando el usuario quiere ver los detalles del pedido -->
  <CompleteOrderInfo
    v-if="orderVisible"
    :orderDelivery="orderDelivery"
    :toggle="toggle"
    :statusChanges="statusChanges"
  />
  </main>
</template>

<script>
import OrderFrontService from '@/services/OrderFrontService'
import DateTimeService from '@/services/DateTimeService'
import CompleteOrderInfo from '@/components/CompleteOrderInfo.vue'
import Swal from 'sweetalert2'
import Datepicker from 'vue3-datepicker'
import moment from 'moment'

export default {
  components: {
    CompleteOrderInfo,
    Datepicker
  },
  data () {
    return {
      orderDeliveries: [],
      orderDeliveriesToShow: [],
      delivery: {},
      statuses: [],
      orderDelivery: '',
      statusChanges: [],
      orderVisible: false,
      deliveryDate: ''
    }
  },
  methods: {
    getAllOrders () {
      OrderFrontService.getOrders()
        .then((response) => {
          this.orderDeliveries = response.data
          this.orderDeliveriesToShow = response.data
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
    filterByStatus (status) {
      this.orderDeliveriesToShow = this.orderDeliveries.filter(orderDelivery => orderDelivery.order.orderStatus.id === status.id)
    },
    filterByDeliveryDate (date) {
      console.log(date)
      this.orderDeliveriesToShow = this.orderDeliveries.filter(orderDelivery => {
        const deliveryDate = moment(orderDelivery.date, 'YYYY-MM-DD')
        const selectedDate = moment(date, 'YYYY-MM-DD')
        return deliveryDate.isSame(selectedDate)
      })
    },
    clearFilter () {
      this.orderDeliveriesToShow = this.orderDeliveries
    },
    prettyDate (timestamp, locale) {
      return DateTimeService.prettyDateShort(timestamp, locale)
    },
    prettyDateFromDate (date, locale) {
      return DateTimeService.prettyDateFromDate(date, locale)
    },
    toggle () {
      this.orderVisible = !this.orderVisible
    },
    viewOrder (orderDelivery) {
      const json = {
        id: orderDelivery.order.id
      }
      OrderFrontService.getStatusChanges(json)
        .then((response) => {
          this.statusChanges = response.data
        })
        .catch(error => {
          console.log(error.response.data)
        })
      this.orderDelivery = orderDelivery
      console.log(orderDelivery)
      this.toggle()
    }
  },
  created () {
    this.getAllOrders()
    this.getOrderStatuses()
  }
}
</script>