import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ProductsView from '../views/ProductsView'
import PastOrders from '../views/PastOrders'
import AddNewProduct from '../views/AddNewProduct'
import InfoProduct from '../views/InfoProduct'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/products',
    name: 'products',
    component: ProductsView
  },
  {
    path: '/past-orders',
    name: 'PastOrders',
    component: PastOrders
  },
  {
    path: '/add-new-product',
    name: 'AddNewProduct',
    component: AddNewProduct
  },
  {
    path: '/info-products/:id',
    name: 'InfoProduct',
    component: InfoProduct
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
