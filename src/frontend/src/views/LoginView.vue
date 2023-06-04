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
                <button type="submit" class="user-submit">{{ $t('user.buttonSI') }}</button>
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
        console.log('this is checkform method in loginView')
        UserFrontService.loginUser(user).then((response) => {
          console.log(response.data)
          // si la respuesta del backend ha sido correcta,
          // nos devuelve el token que guardamos en local storage
          localStorage.setItem('token', response.data)
          // recibimos los datos del usuario desde el backend
          // y emitimos el objeto user con sus datos
          UserFrontService.getUser(user).then((response) => {
            console.log(response.data)
            localStorage.setItem('user', JSON.stringify(response.data))
            this.emitter.emit('loggedUser', response.data)
            this.$router.push('/profile')
          }).catch((error) => {
            this.errorCred = true
            console.log(error)
          })
        }).catch((error) => {
          this.errorCred = true
          console.log(error)
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