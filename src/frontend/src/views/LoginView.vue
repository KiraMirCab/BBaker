<template>
    <main class="wrapper">
        <h1>{{ $t('user.signIn') }}</h1>
        <div class="container">
            <form>
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
                <button type="submit" @click="checkform" class="submit">{{ $t('user.buttonSI') }}</button>
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
      errorEmail: false,
      errorPass: false
    }
  },
  methods: {
    checkform () {
      if (this.email.match('/^S+@S+.S+$/') && this.pass) {
        this.errorEmail = false
        this.errorPass = false
        const user = {
          email: this.email,
          pass: this.pass
        }
        // ProductService.createNewProduct(newPruduct)
        // .then((response) => {this.dbProduct = response.data})
      } else {
        if (!this.email.match('/^S+@S+.S+$/')) {
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