<!--Menu lateral de usuario-->
<template>
    <aside class="user-menu-container">
      <div class="user">
        <div class="user-menu-link">
          <i class="icofont-user-alt-5 icofont-1x"></i>
          <button @click="toggle" class="user-menu-close">&times;</button>
        </div>
        <div>
            <router-link to="/register" class="user-menu-link" @click="toggle">
                <span>{{ $t('user.signUp') }}</span>
            </router-link>
        </div>
        <div v-if="!logged">
            <router-link to="/login" class="user-menu-link" @click="toggle">
                <span>{{ $t('user.signIn') }}</span>
            </router-link>
          </div>
        <div v-if="logged">
            <router-link to="/profile" class="user-menu-link" @click="toggle">
                <span>{{ $t('user.profile') }}</span>
            </router-link>
        </div>
            <div @click="logUserOut" class="user-menu-link" v-if="logged">
                <span>{{ $t('user.logout') }}</span>
            </div>
      </div>
    </aside>
  </template>

<script>
import router from '@/router'

export default {
  props: ['toggle', 'user'],
  data () {
    return {
      logged: localStorage.user
    }
  },
  methods: {
    logUserOut () {
      localStorage.removeItem('user')
      this.$router.push('/')
      this.toggle()
    }
  }
}
</script>