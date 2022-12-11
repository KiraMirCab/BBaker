import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/register',
    name: 'register',
    component: () => import(/* webpackChunkName: "about" */ '../views/RegisterView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import(/* webpackChunkName: "about" */ '../views/Profile.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.user) return '/login'
    }
  },
  {
    path: '/products',
    name: 'products',
    component: () => import(/* webpackChunkName: "about" */ '../views/ProductsView.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.user) return '/login'
    }
  },
  {
    path: '/past-orders',
    name: 'PastOrders',
    component: () => import(/* webpackChunkName: "about" */ '../views/PastOrders.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.user) return '/login'
    }
  },
  {
    path: '/add-new-product',
    name: 'AddNewProduct',
    component: () => import(/* webpackChunkName: "about" */ '../views/AddNewProduct.vue'),
    beforeEnter: (to, from) => {
      if (!localStorage.user) return '/login'
    }
  },
  {
    path: '/info-products/:id',
    name: 'InfoProduct',
    component: () => import(/* webpackChunkName: "about" */ '../views/InfoProduct.vue')
  },
  {
    path: '/update-products/:id',
    name: 'UpdateProduct',
    component: () => import(/* webpackChunkName: "about" */ '../views/UpdateProduct.vue')
  },
  {
    path: '/new-order/:id',
    name: 'NewOrder',
    component: () => import(/* webpackChunkName: "about" */ '../views/NewOrder.vue')
  },
  {
    path: '/orders-now',
    name: 'OrdersNow',
    component: () => import(/* webpackChunkName: "about" */ '../views/OrdersNow.vue')
  },
  {
    path: '/map-deliveries',
    name: 'MapDeliveries',
    component: () => import(/* webpackChunkName: "about" */ '../views/MapDeliveries.vue')
  },
  {
    path: '/find-user',
    name: 'FindUser',
    component: () => import(/* webpackChunkName: "about" */ '../views/FindUser.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
