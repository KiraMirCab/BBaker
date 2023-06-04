<template>
    <main class="wrapper">
        <h1 class="center">{{ $t('user.profile') }}</h1>
        <div class="container">
          <div class="row padding-10">
            <div class="col-md-2 bold"> Name: </div>
            <div class="col-md">{{this.user.userFName}} {{this.user.userLName}}</div>
          </div>
          <div class="row padding-10">
            <div class="col-md-2 bold"> Email: </div>
            <div class="col-md">{{this.user.userEmail}}</div>
          </div>
          <!-- El rol del usuario solo se mostrará cuando no es cliente -->
          <div v-if="this.user.userRole !== 'CLIENT'" class="row padding-10">
            <div class="col-md-2 bold"> Permissons: </div>
            <div class="col-md">{{this.user.userRole}}</div>
          </div>
        </div>
    </main>
</template>

<script>

export default {
  data () {
    return {
      user: {}
    }
  },
  created () {
    this.getUser()
  },
  methods: {
    getUser () {
      this.user = JSON.parse(localStorage.user)
    }
  }
}
</script>