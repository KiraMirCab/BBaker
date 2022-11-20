<template>
    <main class="wrapper">
        <h1 class="center">{{ $t('user.signUp') }}</h1>
        <div class="container">
          <div class="card-container">
            <form v-if="!requestSent">
                <div class="form-group">
                    <label for="fName">{{ $t('user.fName') }}:</label>
                    <input v-model.trim="fName" type="text" id="fName" class="form-control"/>
                    <p id="fNameHelp" class="error" v-if="errorfName">{{ $t('user.error.fName') }}</p>
                </div>
                <div class="form-group">
                    <label for="lName">{{ $t('user.lName') }}:</label>
                    <input v-model.trim="lName" type="text" id="lName" class="form-control"/>
                    <p id="lNameHelp" class="error" v-if="errorlName">{{ $t('user.error.lName') }}</p>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input v-model.trim="email" type="email" id="email" class="form-control"/>
                    <p id="emailHelp" class="error" v-if="errorEmail">{{ $t('user.error.email') }}</p>
                </div>
                <div class="form-group">
                    <label for="pass">{{ $t('user.pass') }}:</label>
                    <input v-model.trim="pass" type="text" id="pass" class="form-control"/>
                    <p id="passHelp" class="error" v-if="errorPass">{{ $t('user.error.pass') }}</p>
                </div>
                <div class="user-cell">
                    <p>{{ $t('user.yesAcc') }}</p>
                    <router-link to="/login" class="user-link">
                    <span>{{ $t('user.buttonSI') }}</span>
                    </router-link>
                  </div>
                <button @click="checkform" class="user-submit">{{ $t('user.buttonSU') }}</button>
            </form>
            <div v-if="requestSent">
              <p>{{ $t('user.emailSent') }}</p>
            </div>
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
      fName: '',
      lName: '',
      token: '',
      errorfName: false,
      errorlName: false,
      errorEmail: false,
      errorPass: false,
      requestSent: false
    }
  },
  methods: {
    checkform () {
      if (this.fName && this.lName && this.email && this.pass.length >= 4) {
        this.errorfName = false
        this.errorlName = false
        this.errorEmail = false
        this.errorPass = false
        const user = {
          firstName: this.fName,
          lastName: this.lName,
          email: this.email,
          password: this.pass
        }
        UserFrontService.registerNewUser(user).then((response) => {
          this.token = response.data
        })
        this.requestSent = true
      } else {
        if (this.fName === '') {
          this.errorfName = true
        }
        if (this.lName === '') {
          this.errorlName = true
        }
        if (this.email === '') {
          this.errorEmail = true
        }
        if (this.pass.length < 4) {
          this.errorPass = true
        }
      }
    }
  }
}
</script>