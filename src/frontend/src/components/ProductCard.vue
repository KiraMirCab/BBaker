<template>
  <div class="card">
    <div class="card-title">
      {{ product.name }}
    </div>
    <div class="card-body">
      <img class="image" :src="product.image" alt="Image from the db" />
      <form>
        <div class="row">
          <div class="cell">
            <label>Descripción:</label>
          </div>
          <div class="cell">
            <em>{{ product.shortDesc }}</em>
          </div>
        </div>
        <div class="row">
          <div class="cell">
            <label>Precio:</label>
          </div>
          <div class="cell">€{{ product.price }}</div>
        </div>
        <div class="row">
          <div class="cell">
            <label>Cantidad:</label>
          </div>
          <div class="cell">
            <input type="number" v-model.number="quantity" />
          </div>
        </div>
      </form>
    </div>
    <div class="card-footer">
      <button @click="addToCart(product.name, quantity)" class="btn btn-light">
        Al carrito
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