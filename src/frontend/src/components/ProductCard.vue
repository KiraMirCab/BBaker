<template>
  <div class="card">
    <div class="card-title" v-if="this.$i18n.locale === 'es'">{{ product.name }}</div>
    <div class="card-title" v-else>{{ product.nameENG }}</div>
    <div class="card-body">
      <img class="image" :src="product.image" alt="Image from the db" />
      <form>
        <div class="row">
          <div class="cell">
            <label>{{ $t('card.description') }}:</label>
          </div>
          <div class="cell">
            <em v-if="this.$i18n.locale === 'es'">{{ product.shortDesc }}</em>
            <em v-else>{{ product.shortDescENG }}</em>
          </div>
        </div>
        <div class="row">
          <div class="cell">
            <label>{{ $t('card.price') }}:</label>
          </div>
          <div class="cell">€{{ product.price }}</div>
        </div>
        <div class="row">
          <div class="cell">
            <label>{{ $t('card.quantity') }}:</label>
          </div>
          <div class="cell">
            <input type="number" v-model.number="quantity" />
          </div>
        </div>
      </form>
    </div>
    <div class="card-footer">
      <button @click="addToCart(product.name, quantity)" class="btn btn-light">
        {{ $t('card.cart') }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['product', 'index', 'addToCart'],
  data () {
    return {
      quantity: 0
    }
  }
}
</script>