<template>
  <div class="card">
    <!--el routerlink está puesto para los elementos principales de la card
    para redirigir a la página del producto. En la url recoge el id del producto.
    El routerPath se recoge de la vista padre en la que se encuentra esta card-->
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
      <p class="badge bg-success" v-if="update && product.active">{{ $t("card.active") }}</p>
      <p class="badge bg-secondary" v-if="update && !product.active">{{ $t("card.notactive") }}</p>
    </router-link>
    <div class="card-body">
      <router-link :to="routerPath + product.id" class="card-link">
        <img class="image" :src="product.image" alt="Image from the db" />
      </router-link>
      <form>
        <router-link :to="routerPath + product.id" class="card-link">
          <div class="row padding-top-15">
            <!-- <div class="cell">
              <label>{{ $t("card.description") }}:</label>
            </div> -->
            <div class="col">
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
      <button v-if="update && product.active" @click="toggleActive()" class="btn btn-dark">
        {{ $t("card.deactivate") }}
      </button>
      <button v-if="update && !product.active" @click="toggleActive()" class="btn btn-light">
        {{ $t("card.activate") }}
      </button>
      <button @click="addToCart(product.name, quantity)" class="btn btn-light ms-auto">
        {{ $t("card.cart") }}
      </button>
    </div>
  </div>
</template>

<script>
import ProductService from '@/services/ProductService.js'
import Swal from 'sweetalert2'

export default {
  props: ['product', 'index', 'addToCart', 'routerPath', 'update'],
  data () {
    return {
      quantity: 1
    }
  },
  methods: {
    onClick () {
      this.$emit('clicked', this.product)
    },
    toggleActive () {
      const newProduct = this.product
      newProduct.active = !this.product.active
      ProductService.createNewProduct(newProduct)
        .then(response => {
          console.log(response.data)
          Swal.fire({
            icon: 'success',
            title: this.$t('product.save'),
            showConfirmButton: false,
            timer: 1500
          })
        })
        .catch(error => {
          console.log(error.response.status)
          console.log(error.response.data)
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: error.response.data.message
          })
        })
    }
  }
}
</script>