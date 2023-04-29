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
    :user="user"
    :toggle="toggleUserMenu"
    :logout="logOut"
    :userlogged="toggleLogged"
  />
  <vue-confirm-dialog></vue-confirm-dialog>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import UserMenu from '@/components/UserMenu.vue'
import ProductService from '@/services/ProductService.js'
import UserFrontService from './services/UserFrontService'
import Swal from 'sweetalert2'
import useEventsBus from './eventBus'
import { watch } from 'vue'

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
      // get the user from the DB and get the user ID
      if (localStorage.user) {
        const json = {
          email: localStorage.useremail,
          password: 'pass'
        }
        console.log(json)
        UserFrontService.getUser(json).then((response) => {
          this.user = response.data
          this.userID = this.user.userID
          localStorage.setItem('user_id', this.user.userID)
          if (this.user.userRole === 'ADMIN') {
            this.admin = true
            this.employee = true
            this.delivery = true
          } else if (this.user.userRole === 'EMPLOYEE') {
            this.employee = true
          } else if (this.user.userRole === 'DELIVERY') {
            this.delivery = true
          } else {
            this.logged = true
          }
        })
      }
    },
    toggleLogged () {
      this.logged = !this.logged
    },
    logOut () {
      this.user = {}
      this.userID = ''
      localStorage.removeItem('useremail')
      localStorage.removeItem('user_id')
      this.admin = false
      this.employee = false
      this.delivery = false
      this.logged = false
    }
  },
  created () {
    this.getProducts()
    this.getUser()
    // const { bus } = useEventsBus()
    // watch(() => bus.value.get('product-updated'), (val) => {
    //   this.getProducts()
    // })
  }
}
</script>
