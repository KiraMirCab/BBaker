<template>
  <div class="card">
    <router-link
      :to="routerPath + product.id"
      custom
      v-slot="{ href, navigate }"
    >
      <a :href="href" @click="onClick($event, navigate)" class="card-link">
        <div class="card-title" v-if="this.$i18n.locale === 'es'">
          {{ product.name }}
        </div>
        <div class="card-title" v-else>{{ product.nameENG }}</div>
      </a>
    </router-link>
    <div class="card-body">
      <router-link :to="routerPath + product.id" class="card-link">
        <img class="image" :src="product.image" alt="Image from the db" />
      </router-link>
      <form>
        <router-link :to="routerPath + product.id" class="card-link">
          <div class="row">
            <div class="cell">
              <label>{{ $t("card.description") }}:</label>
            </div>
            <div class="cell">
              <em v-if="this.$i18n.locale === 'es'">{{ product.shortDesc }}</em>
              <em v-else>{{ product.shortDescENG }}</em>
            </div>
          </div>
          <div class="row">
            <div class="cell">
              <label>{{ $t("card.price") }}:</label>
            </div>
            <div class="cell">€{{ product.price }}</div>
          </div>
        </router-link>
        <div class="row">
          <div class="cell">
            <label>{{ $t("card.quantity") }}:</label>
          </div>
          <div class="cell">
            <select v-model.number="quantity">
              <option disabled value="">{{ $t("product.quantity") }}</option>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
              <option>10</option>
              <option>25</option>
            </select>
          </div>
        </div>
      </form>
    </div>
    <div class="card-footer">
      <button @click="addToCart(product.name, quantity)" class="btn btn-light">
        {{ $t("card.cart") }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['product', 'index', 'addToCart', 'routerPath'],
  data () {
    return {
      quantity: 0
    }
  },
  methods: {
    onClick () {
      this.$emit('clicked', this.product)
    }
  }
}
</script>