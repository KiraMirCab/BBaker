<template>
    <main class="wrapper">
        <h1 class="center">{{ $t('user.profile') }}</h1>
        <div class="container">
                <div>
                  <p>Name : {{this.user.userFName}} {{this.user.userLName}}</p>
                  <p>User's email is : {{this.user.userEmail}}</p>
        </div>
      </div>
    </main>
</template>

<script>
import UserFrontService from '@/services/UserFrontService.js'

export default {
  data () {
    return {
      user: {},
      userID: '',
      email: ''
    }
  },
  created () {
    this.getUser()
  },
  methods: {
    getUser () {
      // get the user from the DB and get the user ID
      const json = {
        email: localStorage.useremail,
        password: 'pass'
      }
      console.log(json)
      UserFrontService.getUser(json).then((response) => {
        this.user = response.data
        this.userID = this.user.userID
      })
    }
  }
}
</script>