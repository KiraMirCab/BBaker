import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import './assets/styles/style.scss'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import i18n from './i18n'
import VueConfirmPlugin from 'v3confirm'

createApp(App)
  .use(i18n)
  .use(router)
  .use(VueConfirmPlugin, {
    root: '#confirm',
    yesText: 'Yes',
    noText: 'No'
  })
  .mount('#app')
