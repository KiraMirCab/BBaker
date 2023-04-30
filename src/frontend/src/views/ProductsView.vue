<template>
  <main class="wrapper">
    <div class="prodcard">
    <h1>{{ $t("product.manage") }}</h1>
    <div class="card-container">
      <ProductCard
        v-for="(product, index) in inventory"
        :key="product.id"
        class="card"
        :index="index"
        :product="product"
        :addToCart="addToCart"
        :routerPath="routerPath"
        :update=true
      />
    </div>
  </div>
  </main>
</template>

<script>
import ProductCard from '@/components/ProductCard.vue'

export default {
  name: 'Products',
  props: ['inventory', 'addToCart', 'getProducts'],
  components: {
    ProductCard
  },
  data () {
    return {
      routerPath: '/update-products/'
    }
  },
  created () {
    this.getProducts()
  }
}
</script>
