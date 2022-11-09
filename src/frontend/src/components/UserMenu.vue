<!--El carrito-->
<template>
    <aside class="cart-container">
      <div class="cart">

        <div class="cart-body">
            <router-link to="/register">
                <span>Sign up</span>
            </router-link>
          <div>Sign in</div>
          <div>My profile</div>
        </div>
      </div>
    </aside>
  </template>

<script>
import router from '@/router'

export default {
  props: ['userID'],
  data () {
    return {
    }
  },
  methods: {
  }
}
</script>