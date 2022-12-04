<template>
    <main class="wrapper">
        <h1 class="center">{{ $t('user.signIn') }}</h1>
        <div class="container">
          <div class="card-container">
            <form @submit.prevent="checkform">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input v-model.trim="email" type="email" id="email" class="form-control"/>
                    <p id="emailHelp" class="error" v-if="errorEmail">{{ $t('user.error.email') }}</p>
                </div>
                <div class="form-group">
                    <label for="pass">{{ $t('user.pass') }}:</label>
                    <input v-model.trim="pass" type="text" id="pass" class="form-control"/>
                    <p id="passHelp" class="error" v-if="errorPass">{{ $t('user.error.passReq') }}</p>
                </div>
                  <div class="user-cell">
                    <p>{{ $t('user.noAcc') }}</p>
                    <router-link to="/register" class="user-link">
                    <span>{{ $t('user.buttonSU') }}</span>
                    </router-link>
                  </div>
                <p class="error" v-if="errorCred">{{ $t('user.error.wrong') }}</p>
                <button type="submit" @click="checkform" class="user-submit">{{ $t('user.buttonSI') }}</button>
            </form>
          </div>
        </div>
    </main>
</template>

<script>
import UserFrontService from '@/services/UserFrontService.js'

export default {
  data () {
    return {
      email: '',
      pass: '',
      errorEmail: false,
      errorPass: false,
      errorCred: false
    }
  },
  methods: {
    checkform () {
      if (this.email && this.pass) {
        this.errorEmail = false
        this.errorPass = false
        const user = {
          email: this.email,
          password: this.pass
        }
        UserFrontService.loginUser(user).then((response) => {
          console.log(response.data)
          this.token = response.data.token
          localStorage.setItem('user', this.token)
          this.$router.push('/profile')
        }).catch((error) => {
          this.errorCred = true
          console.log(error.response)
        })
      } else {
        if (!this.email) {
          this.errorEmail = true
        }
        if (this.pass === '') {
          this.errorPass = true
        }
      }
    }
  }
}
</script>