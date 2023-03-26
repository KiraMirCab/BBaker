<!--El carrito-->
<template>
  <aside class="cart-container">
    <div class="cart">
      <h1 class="cart-title spread">
        <span>
          {{ $t("menu.cart") }}
          <i class="icofont-cart-alt icofont-1x"></i>
        </span>
        <button @click="toggle" class="cart-close">&times;</button>
      </h1>

      <div class="cart-body">
        <table class="cart-table">
          <thead>
            <tr>
              <th>{{ $t("cart.product") }}</th>
              <th>{{ $t("cart.price") }}</th>
              <th>{{ $t("cart.qty") }}</th>
              <th>Total</th>
              <th><span class="sr-only">Actions</span></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(quantity, key, i) in cart" :key="i">
              <td>{{ getName(key) }}</td>
              <td>{{ getPrice(key) }}€</td>
              <td class="center">{{ quantity }}</td>
              <td>{{ (quantity * getPrice(key)).toFixed(2) }}€</td>
              <td class="center">
                <button @click="remove(key)" class="btn btn-light cart-remove">
                  &times;
                </button>
              </td>
            </tr>
          </tbody>
        </table>
        <p class="center" v-if="!Object.keys(cart).length">
          <em>{{ $t("cart.noitems") }}</em>
        </p>
        <br />
        <div class="spread">
          <span><strong>Total:</strong> {{ calculateTotal() }}€</span>
          <button v-if="Object.keys(this.cart).length !== 0" class="btn btn-light" @click="createOrder">{{ $t("cart.checkout") }}</button>
        </div>
      </div>
    </div>
  </aside>
</template>

<script>
import router from '@/router'
import OrderFrontService from '@/services/OrderFrontService.js'

export default {
  props: ['toggle', 'cart', 'inventory', 'remove', 'userID', 'emptyCart'],
  data () {
    return {
      quantity: '',
      orderID: ''
    }
  },
  methods: {
    getProduct (name) {
      return this.inventory.find((p) => {
        return p.name === name
      })
    },
    getName (name) {
      if (this.$i18n.locale === 'es') {
        return name
      } else {
        return this.getProduct(name).nameENG
      }
    },
    getPrice (name) {
      return this.getProduct(name).price
    },

    getId (name) {
      return this.getProduct(name).id
    },

    calculateTotal () {
      const total = Object.entries(this.cart).reduce((acc, curr, index) => {
        return acc + curr[1] * this.getPrice(curr[0])
      }, 0)
      return total.toFixed(2)
    },
    createOrder () {
      // Creamos un objeto para el nuevo pedido
      const newOrder = {
        creationDate: Date.now(),
        paidDate: '',
        total: this.calculateTotal(),
        user_id: this.userID,
        // Adjuntamos al objeto los datos de cada producto del carrito
        orderProductsDTO: Object.entries(this.cart).map((p) => {
          return {
            product_id: this.getId(p[0]),
            price: this.getPrice(p[0]),
            quantity: p[1]
          }
        })
      }
      console.log(newOrder)
      // Grabarlos en la base de datos el nuevo pedido y navegamos a la vista
      // del pedido con el id del nuevo pedido de la base de datos
      OrderFrontService.createNewOrder(newOrder).then((response) => {
        router.push('/new-order/' + response.data.id)
        this.emptyCart()
      })
      // cerramos el carrito
      this.toggle()
    }
  }
}
</script>