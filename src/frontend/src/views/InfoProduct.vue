<template>
  <main class="wrapper">
    <div class="prodcard">
      <div class="card-title">
        <h1 v-if="this.$i18n.locale === 'es'" class="center">
          {{ product.name }}
        </h1>
        <h1 v-else class="center">{{ product.nameENG }}</h1>
      </div>
      <div class="prodcard-body">
        <img v-if="product.image" :src="product.image" alt="Image from the db" class="image"/>
        <img v-else :src="require('../assets/img/default.jpg')" alt="Image from the db" class="image"/>
        <p v-if="this.$i18n.locale === 'es'" class="catchy">{{ product.shortDesc }}</p>
        <p v-else class="catchy">{{ product.shortDescENG }}</p>
        <p v-if="this.$i18n.locale === 'es'" class="long">{{ product.description }}</p>
        <p v-else class="long">{{ product.descriptionENG }}</p>
        <p class="price">{{ $t("card.price") }}: {{ product.price }} €</p>
        <p v-if="this.product.specialTransport" class="transport">
          {{ $t("product.transport") }}
        </p>
        <div class="prodcard-footer">
          <div class="row">
            <div class="cell">
              <form>
                <label>{{ $t("card.quantity") }}:</label>
                <select v-model.number="quantity">
                  <option disabled value="">
                    {{ $t("product.quantity") }}
                  </option>
                  <option>1</option>
                  <option>2</option>
                  <option>3</option>
                  <option>4</option>
                  <option>5</option>
                  <option>10</option>
                  <option>25</option>
                </select>
              </form>
            </div>
            <div class="cell">
              <button
                @click="addToCart(product.name, quantity)"
                class="btn btn-light"
              >
                {{ $t("card.cart") }}
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import ProductService from '@/services/ProductService.js'

export default {
  name: 'InfoProduct',
  props: ['addToCart'],
  components: {},
  // en el momento de la creación de la página recogo el id del producto de la url
  // y traigo los datos del producto de la bd
  created () {
    this.id = this.$route.path.split('/')[2]
    this.getProductInfo(this.id)
  },
  data () {
    return {
      id: '',
      product: '',
      quantity: 1
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