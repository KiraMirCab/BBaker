<template>
    <main class="wrapper">
        <h1>Sign up</h1>
        <div class="container">
            <form @submit.prevent="checkform">
                <div class="form-group">
                    <label for="fName">First name:</label>
                    <input v-model.trim="fName" type="text" id="fName" class="form-control"/>
                    <p id="fNameHelp" class="error" v-if="errorfName">First name is required</p>
                </div>
                <div class="form-group">
                    <label for="lName">Last name:</label>
                    <input v-model.trim="lName" type="text" id="lName" class="form-control"/>
                    <p id="lNameHelp" class="error" v-if="errorlName">Last name is required</p>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input v-model.trim="email" type="email" id="email" class="form-control"/>
                    <p id="emailHelp" class="error" v-if="errorEmail">Email is required</p>
                </div>
                <div class="form-group">
                    <label for="pass">Password:</label>
                    <input v-model.trim="pass" type="text" id="pass" class="form-control"/>
                    <p id="passHelp" class="error" v-if="errorPass">Password is required</p>
                </div>
                <button type="submit" @click="checkform" class="submit">Sign up</button>
            </form>
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
      errorPass: false
    }
  },
  methods: {
    checkform () {
      if (this.fName && this.lName && this.email && this.pass) {
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
        UserFrontService.registerNewUser(user).then((response) => { this.token = response.data })
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
        if (this.pass === '') {
          this.errorPass = true
        }
      }
    }
  }
}
</script>