<template>
  <form @submit.prevent="checkform">
    <div class="form-group">
      <label for="pName">Nombre: *</label>
      <input
        v-model.trim="name"
        type="text"
        id="pName"
        class="form-control"
        placeholder="Nombre del producto"
      />
      <p id="emailHelp" class="error" v-if="errorNombre">Falta el nombre</p>
    </div>
    <div class="form-group">
      <img src="../assets/img/ENG.png" class="icon-small" />
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
      <p id="emailHelp" class="error" v-if="errorDesc">Falta la descripcion</p>
    </div>
    <div class="form-group">
      <img src="../assets/img/ENG.png" class="icon-small" />
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
      <img src="../assets/img/ENG.png" class="icon-small" />
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
        >Añada la imagen del producto</small
      >
      <p id="emailHelp" class="error" v-if="errorImagen">
        El fichero es demasiado grande. Intente otro, por favor
      </p>
    </div>
    <div class="form-group input-short">
      <label for="pPrice">Precio: *</label>
      <div class="input-group">
        <input
          v-model.number="price"
          type="text"
          id="pPrice"
          class="form-control"
          placeholder="0"
        />
        <div class="input-group-append">
          <span class="input-group-text" id="basic-addon">€</span>
        </div>
      </div>
      <p id="emailHelp" v-if="errorPrecio" class="error">
        No lo vas a regalar a tol mundo, no?
      </p>
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
    <button type="submit" @click="$emit('checkform', dbProduct)" class="submit">Guardar</button>
  </form>
</template>

<script>
import ProductService from '@/services/ProductService.js'

export default {
  props: ['product'],
  created (product) {
    if (this.product) {
      this.name = product.name
      this.nameENG = product.nameENG
      this.description = product.description
      this.descriptionENG = product.descriptionENG
      this.shortDesc = product.shortDesc
      this.shortDescENG = product.shortDescENG
      this.image = product.image
      this.price = product.price
      this.discount = product.discount
      this.specialTransport = product.specialTransport
    }
  },
  data () {
    return {
      name: '',
      nameENG: '',
      description: '',
      descriptionENG: '',
      shortDesc: '',
      shortDescENG: '',
      image: '',
      price: '',
      discount: '0',
      specialTransport: false,
      dbProduct: '',
      maxLength: 50,
      errorNombre: false,
      errorDesc: false,
      errorPrecio: false,
      errorImagen: false
    }
  },
  methods: {
    checkform () {
      if (this.name && this.shortDesc && this.price) {
        this.errorNombre = false
        this.errorDesc = false
        this.errorPrecio = false
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
        this.dbProduct = newPruduct
        ProductService.createNewProduct(newPruduct)
        // .then((response) => {this.dbProduct = response.data})
      } else {
        if (this.name === '') {
          this.errorNombre = true
        }
        if (this.shortDesc === '') {
          this.errorDesc = true
        }
        if (this.price === '') {
          this.errorPrecio = true
        }
      }
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
          this.errorImagen = true
        }
      }
    }
  }
}
</script>