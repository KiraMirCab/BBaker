<template>
  <main class="wrapper">
    <h1>Añade un producto nuevo</h1>
    <div class="container">
      <ProductForm
        class="form"
        :checkform="checkform"
        :onFileSelected="onFileSelected"
        :product="product"
        @checkform="(dbProduct) => this.product=dbProduct"
        />
    </div>
  </main>
</template>

<script>
import ProductForm from '@/components/ProductForm.vue'

export default {
  components: {
    ProductForm
  },
  data () {
    return {
      product: null
    }
  }
}
</script>