<template>
  <div class="home">
    <div class="splash-container">
      <div class="splash">
        <h1>BBaker's pastry</h1>
      </div>
    </div>
    <main class="wrapper">
      <div class="card-container">
        <ProductCard
          v-for="(product, index) in inventory"
          :key="product.id"
          class="card"
          :index="index"
          :product="product"
          :addToCart="addToCart"
          :routerPath="routerPath"
          @clicked="onClickChild"
        />
      </div>
    </main>
  </div>
</template>

<script>
import ProductCard from '@/components/ProductCard.vue'

export default {
  name: 'Home',
  props: ['inventory', 'addToCart'],
  components: {
    ProductCard
  },
  data () {
    return {
      routerPath: '/info-products/',
      currentProduct: ''
    }
  },
  methods: {
    onClickChild (product) {
      this.currentProduct = product
      // this.$emit('clickedChild', product)
    }
  }
}
</script>
