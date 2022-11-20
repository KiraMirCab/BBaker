<template>
    <main class="wrapper">
        <h1 class="center">{{ $t('user.profile') }}</h1>
        <div class="container">
                <div>
                  <p>Name : </p>
                  <p>User's email is : </p>
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
      pass: ''
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