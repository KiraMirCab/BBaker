<template>
    <main class="wrapper">
        <h1>Please log in</h1>
        <div class="container">
            <form>
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
                <button type="submit" @click="checkform" class="submit">Log in</button>
            </form>
        </div>
    </main>
</template>

<script>

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
      if (this.email && this.pass) {
        this.errorEmail = false
        this.errorPass = false
        const user = {
          email: this.email,
          pass: this.pass
        }
        // ProductService.createNewProduct(newPruduct)
        // .then((response) => {this.dbProduct = response.data})
      } else {
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