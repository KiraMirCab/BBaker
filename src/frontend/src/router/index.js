import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView'
import RegisterView from '../views/RegisterView'
import ProductsView from '../views/ProductsView'
import PastOrders from '../views/PastOrders'
import AddNewProduct from '../views/AddNewProduct'
import InfoProduct from '../views/InfoProduct'
import NewOrder from '../views/NewOrder'
import OrdersNow from '../views/OrdersNow'
import MapDeliveries from '../views/MapDeliveries'
import UpdateProduct from '../views/UpdateProduct'
import Profile from '../views/Profile'
import FindUser from '../views/FindUser'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile,
    beforeEnter: (to, from) => {
      if (!localStorage.user) return '/login'
    }
  },
  {
    path: '/products',
    name: 'products',
    component: ProductsView
  },
  {
    path: '/past-orders',
    name: 'PastOrders',
    component: PastOrders,
    beforeEnter: (to, from) => {
      if (!localStorage.user) return '/login'
    }
  },
  {
    path: '/add-new-product',
    name: 'AddNewProduct',
    component: AddNewProduct,
    beforeEnter: (to, from) => {
      if (!localStorage.user) return '/login'
    }
  },
  {
    path: '/info-products/:id',
    name: 'InfoProduct',
    component: InfoProduct
  },
  {
    path: '/update-products/:id',
    name: 'UpdateProduct',
    component: UpdateProduct
  },
  {
    path: '/new-order/:id',
    name: 'NewOrder',
    component: NewOrder
  },
  {
    path: '/orders-now',
    name: 'OrdersNow',
    component: OrdersNow
  },
  {
    path: '/map-deliveries',
    name: 'MapDeliveries',
    component: MapDeliveries
  },
  {
    path: '/find-user',
    name: 'FindUser',
    component: FindUser
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
