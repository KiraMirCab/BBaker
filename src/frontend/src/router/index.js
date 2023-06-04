import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/register/:role(admin|employee|client|delivery)',
    name: 'register',
    component: () => import(/* webpackChunkName: "register" */ '../views/RegisterView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "login" */ '../views/LoginView.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import(/* webpackChunkName: "profile" */ '../views/Profile.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.token) return '/login'
    }
  },
  {
    path: '/products',
    name: 'products',
    component: () => import(/* webpackChunkName: "products" */ '../views/ProductsView.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.token) return '/login'
    },
    key: Date.now() // Recarga la pagina cada vez que se accede a esta ruta
  },
  {
    path: '/past-orders',
    name: 'PastOrders',
    component: () => import(/* webpackChunkName: "past-orders" */ '../views/PastOrders.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.token) return '/login'
    }
  },
  {
    path: '/add-new-product',
    name: 'AddNewProduct',
    component: () => import(/* webpackChunkName: "add-new-product" */ '../views/AddNewProduct.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.token) return '/login'
    }
  },
  {
    path: '/info-products/:id',
    name: 'InfoProduct',
    component: () => import(/* webpackChunkName: "info-product" */ '../views/InfoProduct.vue')
  },
  {
    path: '/update-products/:id',
    name: 'UpdateProduct',
    component: () => import(/* webpackChunkName: "update-product" */ '../views/UpdateProduct.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.token) return '/login'
    }
  },
  {
    path: '/new-order/:id',
    name: 'NewOrder',
    component: () => import(/* webpackChunkName: "new-order" */ '../views/NewOrder.vue')
  },
  {
    path: '/orders-now',
    name: 'OrdersNow',
    component: () => import(/* webpackChunkName: "orders-now" */ '../views/OrdersNow.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.token) return '/login'
    }
  },
  {
    path: '/map-deliveries',
    name: 'MapDeliveries',
    component: () => import(/* webpackChunkName: "map-deliveries" */ '../views/MapDeliveries.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.token) return '/login'
    }
  },
  {
    path: '/find-user',
    name: 'FindUser',
    component: () => import(/* webpackChunkName: "about" */ '../views/FindUser.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.token) return '/login'
    }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
