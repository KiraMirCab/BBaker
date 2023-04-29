<template>
  <main class="wrapper">
    <h1>{{ $t("product.update") }} {{ this.id }}</h1>
    <div class="container">
      <ProductForm
        v-if="product"
        class="form"
        :checkform="checkform"
        :onFileSelected="onFileSelected"
        :product="product"
        />
    </div>
  </main>
</template>

<script>
import ProductForm from '@/components/ProductForm.vue'
import ProductService from '@/services/ProductService.js'

export default {
  name: 'UpdateProduct',
  components: {
    ProductForm
  },
  // en el momento de la creación de la página recojo el id del producto de la url
  // y traigo los datos del producto de la bd
  mounted () {
    this.id = this.$route.path.split('/')[2]
    this.getProductInfo(this.id)
  },
  data () {
    return {
      id: '',
      product: ''
    }
  },
  methods: {
    getProductInfo (id) {
      ProductService.getProductInfo(id).then((response) => {
        this.product = response.data
      })
    }
  }
}
</script>