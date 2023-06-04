<template>
  <header class="top-bar spread">
    <nav class="top-bar-nav">
          <router-link to="/" class="top-bar-link">
            <i class="icofont-cup-cake"></i>
            <span>{{ $t('menu.home') }}</span>
          </router-link>
          <router-link to="/past-orders" class="top-bar-link" v-if="client">
            <span>{{ $t('menu.past') }}</span>
          </router-link>
          <router-link to="/products" class="top-bar-link" v-if="admin">
            <i class="icofont-pen-alt-1"></i>
            <span>{{ $t("product.manage") }}</span>
          </router-link>
          <router-link to="/add-new-product" class="top-bar-link" v-if="admin">
            <i class="icofont-ui-add"></i>
            <span>Añadir producto nuevo</span>
          </router-link>
          <router-link to="/orders-now" class="top-bar-link" v-if="employee">
            <i class="icofont-list"></i>
            <span>Pedidos</span>
          </router-link>
          <router-link to="/map-deliveries" class="top-bar-link" v-if="delivery">
            <i class="icofont-search-map"></i>
            <span>Entregas en el mapa</span>
          </router-link>
          <router-link to="/find-user" class="top-bar-link" v-if="admin">
            <i class="icofont-users-alt-3"></i>
            <span>Listar usuarios</span>
          </router-link>
    </nav>
    <div class="top-bar-nav">
        <div @click="toggleSidebar" class="top-bar-cart-link" v-if="client">
          <i class="icofont-cart-alt"></i>
          <span>{{ $t('menu.cart') }} ({{ totalQuantity }})</span>
        </div>
        <div @click="toggleUserMenu" class="top-bar-user-link">
          <i class="icofont-user-alt-5"></i>
        </div>
        <div @click="toggleLanguage" class="top-bar-lang-link">
           <span>{{ $t('locale') }}</span>
        </div>
    </div>
  </header>
  <router-view
    :inventory="inventory"
    :addToCart="addToCart"
    :getProducts="getProducts"
  />
  <Sidebar
    v-if="showSidebar"
    :toggle="toggleSidebar"
    :cart="cart"
    :inventory="inventory"
    :remove="removeItem"
    :userID="userID"
    :emptyCart="emptyCart"
  />
  <UserMenu
    v-if="showUserMenu"
    :toggle="toggleUserMenu"
    :logout="logOut"
    :logged="this.logged"
  />
  <vue-confirm-dialog></vue-confirm-dialog>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import UserMenu from '@/components/UserMenu.vue'
import ProductService from '@/services/ProductService.js'
import Swal from 'sweetalert2'

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
      user: {},
      userID: 0,
      logged: false,
      admin: false,
      employee: false,
      delivery: false,
      client: false
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
      ProductService.getProducts()
        .then((response) => {
          this.inventory = response.data
        })
        .catch(error => {
          console.log(error.response.data)
        })
    },
    addToCart (productID, quantity) {
      if (!this.cart[productID]) this.cart[productID] = 0
      this.cart[productID] += quantity
      console.log(this.cart)
      quantity = 0
      Swal.fire({
        icon: 'success',
        title: this.$t('cart.save'),
        showConfirmButton: false,
        timer: 1500
      })
    },
    toggleSidebar () {
      this.showSidebar = !this.showSidebar
    },
    removeItem (productID) {
      delete this.cart[productID]
    },
    emptyCart () {
      this.cart = {}
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
    getUser () {
      // este método pone un listener pendiente de los cambio del usuario
      this.emitter.on('loggedUser', eventData => {
        this.updateUser()
      })
    },
    updateUser () {
      const userData = localStorage.getItem('user')
      if (userData) {
        this.user = JSON.parse(userData)
        this.userID = this.user.userID
        this.logged = true

        // Actualiza valores de admin, employee, delivery y client based on user role
        if (this.user.userRole === 'ADMIN') {
          this.admin = true
          this.employee = true
          this.delivery = true
        } else if (this.user.userRole === 'EMPLOYEE') {
          this.employee = true
        } else if (this.user.userRole === 'DELIVERY') {
          this.delivery = true
          this.employee = true
        } else if (this.user.userRole === 'CLIENT') {
          this.client = true
        }
      } else {
        this.user = {}
        this.admin = false
        this.employee = false
        this.delivery = false
        this.client = false
        this.userID = ''
        this.logged = false
      }
    },
    logOut () {
      localStorage.removeItem('token')
      localStorage.removeItem('user')
      this.getUser()
    }
  },
  created () {
    this.getProducts()
    this.updateUser()
    this.getUser()
  }
}
</script>
