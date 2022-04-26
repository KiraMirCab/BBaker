<template>
  <main class="wrapper">
    <h1>Add a new product</h1>
    <div class="container">
      <form class="form">
        <div class="form-group">
          <label for="pName">Nombre: *</label>
          <input
            v-model.trim="name"
            type="text"
            id="pName"
            class="form-control"
            placeholder="Nombre del producto"
          />
        </div>
        <div class="form-group">
          <img src="../assets/img/ENG.png" class="icon-small">
          <label for="pNameENG">Nombre en inglés:</label>
          <input
            v-model.trim="nameENG"
            type="text"
            id="pNameENG"
            class="form-control"
            placeholder="Product's name"
          />
        </div>
        <div class="form-group">
          <label for="pShortDesc">Descripción corta: *</label>
          <div class="input-group">
            <input
              type="text"
              id="pShortDesc"
              class="form-control"
              placeholder="Escriba aquí"
              :maxlength="maxLength"
              v-model="shortDesc"
            />
            <div class="input-group-append">
              <span
                class="input-group-text"
                id="basic-addon"
                v-text="maxLength - shortDesc.length"
              ></span>
            </div>
          </div>
          <small id="emailHelp" class="form-text text-muted"
            >Algo que atraiga al consumidor</small
          >
        </div>
        <div class="form-group">
          <img src="../assets/img/ENG.png" class="icon-small">
          <label for="shortDescENG">Descripción corta en inglés:</label>
          <div class="input-group">
            <input
              type="text"
              id="shortDescENG"
              class="form-control"
              placeholder="Write something here"
              :maxlength="maxLength"
              v-model="shortDescENG"
            />
            <div class="input-group-append">
              <span
                class="input-group-text"
                id="basic-addon"
                v-text="maxLength - shortDescENG.length"
              ></span>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label for="pDescription">Descripción: </label>
          <textarea
            v-model="description"
            id="pDescription"
            class="form-control"
            rows="10"
            placeholder="Escriba aquí la descripción del producto"
          ></textarea>
        </div>
        <div class="form-group">
          <img src="../assets/img/ENG.png" class="icon-small">
          <label for="pDescriptionENG">Descripción en inglés: </label>
          <textarea
            v-model="descriptionENG"
            id="pDescriptionENG"
            class="form-control"
            rows="10"
            placeholder="Write here the description of the product"
          ></textarea>
        </div>
        <div class="form-group">
          <label for="pImage">Imagen: </label>
          <div class="input-group">
            <input
              @change="onFileSelected"
              type="file"
              class="form-control-file"
              id="pImage"
            />
          </div>
          <small id="emailHelp" class="form-text text-muted"
            >Añada la imagen del producto</small>
        </div>
        <div class="form-group input-short">
          <label for="pPrice">Precio: *</label>
          <div class="input-group">
            <input
              v-model.number="price"
              type="text"
              id="pPrice"
              class="form-control"
              placeholder="1"
            />
            <div class="input-group-append">
              <span class="input-group-text" id="basic-addon">€</span>
            </div>
          </div>
        </div>
        <div class="form-group input-short">
          <label for="pDiscount">Descuento para clientes registrados:</label>
          <div class="input-group">
            <input
              v-model.number="discount"
              type="text"
              id="pDiscount"
              class="form-control"
              placeholder="0"
            />
            <div class="input-group-append">
              <span class="input-group-text" id="basic-addon">%</span>
            </div>
          </div>
        </div>
        <div class="checkbox">
          <label class="form-check-label">
            Necesita transporte especial?
            <input
              type="checkbox"
              v-model="specialTransport"
              class="form-check-input"
          /></label>
          <br />
        </div>
        <button type="submit" @click="saveProduct" class="submit">
          Guardar
        </button>
      </form>
    </div>
  </main>
</template>

<script>
import ProductService from '@/services/ProductService.js'

export default {
  data () {
    return {
      name: '',
      nameENG: '',
      description: '',
      descriptionENG: '',
      shortDesc: '',
      shortDescENG: '',
      image: '',
      price: '0',
      discount: '0',
      specialTransport: false,
      dbProduct: '',
      maxLength: 50
    }
  },
  computed: {},
  methods: {
    saveProduct () {
      const newPruduct = {
        name: this.name,
        nameENG: this.nameENG,
        shortDesc: this.shortDesc,
        shortDescENG: this.shortDescENG,
        description: this.description,
        descriptionENG: this.descriptionENG,
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