<template>
  <main class="wrapper">
    <h1>{{ $t("product.update") }} {{ this.id }}</h1>
    <div class="container">
      <ProductForm
        class="form"
        :checkform="checkform"
        :onFileSelected="onFileSelected"
        :product="product"
        :pname="product.name"
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
  // en el momento de la creación de la página recogo el id del producto de la url
  // y traigo los datos del producto de la bd
  created () {
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