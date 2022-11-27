<template>
  <header class="top-bar spread">
    <nav class="top-bar-nav">
          <router-link to="/" class="top-bar-link">
            <i class="icofont-cup-cake icofont-1x"></i>
            <span>{{ $t('menu.home') }}</span>
          </router-link>
          <router-link to="/past-orders" class="top-bar-link" v-if="logged">
            <span>{{ $t('menu.past') }}</span>
          </router-link>
          <router-link to="/products" class="top-bar-link" v-if="admin">
            <span>{{ $t("product.manage") }}</span>
          </router-link>
          <router-link to="/add-new-product" class="top-bar-link" v-if="admin">
            <span>Añadir un producto nuevo</span>
          </router-link>
          <router-link to="/orders-now" class="top-bar-link" v-if="employee">
            <span>Pedidos</span>
          </router-link>
          <router-link to="/map-deliveries" class="top-bar-link" v-if="delivery">
            <span>Entregas en el mapa</span>
          </router-link>
          <router-link to="/find-user" class="top-bar-link" v-if="admin">
            <span>Listar usuarios</span>
          </router-link>
    </nav>
    <div class="top-bar-nav">
        <div @click="toggleSidebar" class="top-bar-cart-link">
          <i class="icofont-cart-alt icofont-1x"></i>
          <span>{{ $t('menu.cart') }} ({{ totalQuantity }})</span>
        </div>
        <div @click="toggleUserMenu" class="top-bar-user-link">
          <i class="icofont-user-alt-5" icofont-1x></i>
        </div>
        <div @click="toggleLanguage" class="top-bar-lang-link">
           <span>{{ $t('locale') }}</span>
        </div>
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
  <UserMenu
    v-if="showUserMenu"
    :userID="userID"
    :toggle="toggleUserMenu"
    :logged="logged"
  />
  <vue-confirm-dialog></vue-confirm-dialog>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import UserMenu from '@/components/UserMenu.vue'
import ProductService from '@/services/ProductService.js'

export default {
  components: {
    Sidebar, UserMenu
  },
  data () {
    return {
      showSidebar: false,
      showUserMenu: false,
      inventory: [],
      cart: {},
      user: '',
      userID: 0,
      logged: localStorage.user,
      role: localStorage.role,
      admin: false,
      employee: false,
      delivery: false
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
    },
    toggleUserMenu () {
      this.showUserMenu = !this.showUserMenu
    },
    togglelogged () {
      this.logged = !this.logged
    }
  },
  created () {
    this.getProducts()
  }
}
</script>
