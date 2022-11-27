import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import './assets/styles/style.scss'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import i18n from './i18n'
import VueSweetalert2 from 'vue-sweetalert2'
import 'sweetalert2/dist/sweetalert2.min.css'

createApp(App)
  .use(i18n)
  .use(router)
  .use(VueSweetalert2)
  .mount('#app')
