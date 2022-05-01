<template>
  <main class="wrapper">
    <h1>Productos</h1>
    <div class="card-container">
      <ProductCard
        v-for="(product, index) in inventory.filter((product) => !product.active)"
        :key="product.id"
        class="card"
        :index="index"
        :product="product"
        :addToCart="addToCart"
        :routerPath="routerPath"
      />
    </div>
  </main>
</template>

<script>
import ProductCard from '@/components/ProductCard.vue'

export default {
  name: 'Products',
  props: ['inventory', 'addToCart'],
  components: {
    ProductCard
  },
  data () {
    return {
      routerPath: '/update-products/'
    }
  }
}
</script>
