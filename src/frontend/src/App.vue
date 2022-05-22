<template>
  <header class="top-bar spread">
        <nav class="top-bar-nav">
          <router-link to="/" class="top-bar-link">
            <img src='./assets/img/icon.png' class="icon-small">
            <span>{{ $t('menu.home') }}</span>
          </router-link>
          <router-link to="/past-orders" class="top-bar-link">
            <span>{{ $t('menu.past') }}</span>
          </router-link>
          <router-link to="/products" class="top-bar-link">
            <span>Modificar productos</span>
          </router-link>
          <router-link to="/add-new-product" class="top-bar-link">
            <span>Añadir un producto nuevo</span>
          </router-link>
        </nav>
        <div @click="toggleSidebar" class="top-bar-cart-link">
          <i class="icofont-cart-alt icofont-1x"></i>
          <span>{{ $t('menu.cart') }} ({{ totalQuantity }})</span>
        </div>
        <div @click="toggleLanguage" class="top-bar-lang-link">
           <span>{{ $t('locale') }}</span>
        </div>
      </header>
  <router-view
    :inventory="inventory"
    :addToCart="addToCart"
  />
  <Sidebar
    v-if="showSidebar"
    :toggle="toggleSidebar"
    :cart="cart"
    :inventory="inventory"
    :remove="removeItem"
    :userID="userID"
  />
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import ProductService from '@/services/ProductService.js'

export default {
  components: {
    Sidebar
  },
  data () {
    return {
      showSidebar: false,
      inventory: [],
      cart: {},
      userID: 0
    }
  },
  computed: {
    totalQuantity () {
      return Object.values(this.cart).reduce((acc, curr) => {
        return acc + curr
      }, 0)
    }
  },
  methods: {
    getProducts () {
      ProductService.getProducts().then((response) => {
        this.inventory = response.data
      })
    },
    getUserID () {
      // get the userID from the DB and
    },
    addToCart (productID, quantity) {
      if (!this.cart[productID]) this.cart[productID] = 0
      this.cart[productID] += quantity
      console.log(this.cart)
      quantity = 0
    },
    toggleSidebar () {
      this.showSidebar = !this.showSidebar
    },
    removeItem (productID) {
      delete this.cart[productID]
    },
    toggleLanguage () {
      if (this.$i18n.locale === 'en') {
        this.$i18n.locale = 'es'
      } else {
        this.$i18n.locale = 'en'
      }
    }
  },
  created () {
    this.getProducts()
  }
}
</script>
