import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'
import './assets/styles/style.scss'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import i18n from './i18n'
import VueSweetalert2 from 'vue-sweetalert2'
import 'sweetalert2/dist/sweetalert2.min.css'
import mitt from 'mitt'

const app = createApp(App)
const emitter = mitt()

app.use(router)
app.use(i18n)
app.use(VueSweetalert2)
app.config.globalProperties.emitter = emitter

app.mount('#app')