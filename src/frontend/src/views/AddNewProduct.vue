<template>
  <main class="wrapper">
    <h1>Add a new product</h1>
    <form>
      <div class="form-group">
        <label for="pName">Name: </label>
        <input
          v-model.trim="name"
          type="text"
          id="pName"
          placeholder="Product's name"
        />
      </div>
      <div class="form-group">
        <label for="pDescription">Description: </label>
        <textarea
          v-model="description"
          id="pDescription"
          rows="5"
          placeholder="Add the description here"
        ></textarea>
      </div>
      <div class="form-group">
        <label for="pImage">Image: </label>
        <input @change="onFileSelected" type="file" id="pImage" />
        <p class="help-block">
          Please add a picture for the product. Make sure it's not too blury
        </p>
      </div>
      <div class="form-group">
        <label for="pPrice">Price: </label>
        <input
          v-model.number="price"
          type="text"
          id="pPrice"
          placeholder="1"
        /><span>€</span>
      </div>
      <div class="form-group">
        <label for="pDiscount">Discount for registered clients: </label>
        <input
          v-model.number="discount"
          type="text"
          id="pDiscount"
          placeholder="0"
        /><span>%</span>
      </div>
      <div class="checkbox">
        <label>
          Does it need a special transport?
          <input type="checkbox" v-model="specialTransport"
        /></label>
        <br />
      </div>
      <button type="submit" @click="saveProduct" class="btn btn-default">
        Submit
      </button>
    </form>
  </main>
</template>

<script>
import ProductService from '@/services/ProductService.js'

export default {
  data () {
    return {
      name: '',
      description: '',
      image: '',
      price: '0',
      discount: '0',
      specialTransport: false,
      dbProduct: ''
    }
  },
  computed: {},
  methods: {
    saveProduct () {
      const newPruduct = {
        name: this.name,
        description: this.description,
        image: this.image,
        price: this.price,
        discount: this.discount,
        specialTransport: this.specialTransport
      }
      ProductService.createNewProduct(newPruduct).then((response) => {
        this.dbProduct = response.data
      })
    },
    onFileSelected (event) {
      const reader = new FileReader()
      reader.readAsDataURL(event.target.files[0])
      reader.onerror = function (error) {
        console.log('Error: ', error)
      }
      reader.onload = () => {
        this.image = reader.result
        if (reader.result.length > 16777214) {
          alert('El fichero es demasiado grande. Intente otro, por favor')
        }
      }
    }
  }
}
</script>