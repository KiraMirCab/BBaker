<template>
  <form @submit.prevent="checkform">
    <div class="form-group">
      <label for="pName">Nombre: *</label>
      <input v-model.trim="name" type="text" id="pName" class="form-control" placeholder="Nombre del producto" />
      <p id="emailHelp" class="error" v-if="errorNombre">Falta el nombre</p>
    </div>
    <div class="form-group">
      <img src="../assets/img/ENG.png" class="icon-small" />
      <label for="pNameENG">Nombre en inglés:</label>
      <input v-model.trim="nameENG" type="text" id="pNameENG" class="form-control" placeholder="Product's name" />
    </div>
    <div class="form-group">
      <label for="pShortDesc">Descripción corta: *</label>
      <div class="input-group">
        <input type="text" id="pShortDesc" class="form-control" placeholder="Escriba aquí" :maxlength="maxLength"
          v-model="shortDesc" />
        <div class="input-group-append">
          <span class="input-group-text" id="basic-addon" v-text="maxLength - shortDesc.length"></span>
        </div>
      </div>
      <small id="emailHelp" class="form-text text-muted">Algo que atraiga al consumidor</small>
      <p id="emailHelp" class="error" v-if="errorDesc">Falta la descripcion</p>
    </div>
    <div class="form-group">
      <img src="../assets/img/ENG.png" class="icon-small" />
      <label for="shortDescENG">Descripción corta en inglés:</label>
      <div class="input-group">
        <input type="text" id="shortDescENG" class="form-control" placeholder="Write something here"
          :maxlength="maxLength" v-model="shortDescENG" />
        <div class="input-group-append">
          <span class="input-group-text" id="basic-addon" v-text="maxLength - shortDescENG.length"></span>
        </div>
      </div>
    </div>
    <div class="form-group">
      <label for="pDescription">Descripción: </label>
      <textarea v-model="description" id="pDescription" class="form-control" rows="10"
        placeholder="Escriba aquí la descripción del producto"></textarea>
    </div>
    <div class="form-group">
      <img src="../assets/img/ENG.png" class="icon-small" />
      <label for="pDescriptionENG">Descripción en inglés: </label>
      <textarea v-model="descriptionENG" id="pDescriptionENG" class="form-control" rows="10"
        placeholder="Write here the description of the product"></textarea>
    </div>
    <div class="form-group input-short">
      <label for="pPrice">Precio: *</label>
      <div class="input-group">
        <input v-model.number="price" type="text" id="pPrice" class="form-control" placeholder="0" />
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
        <input v-model.number="discount" type="text" id="pDiscount" class="form-control" placeholder="0" />
        <div class="input-group-append">
          <span class="input-group-text" id="basic-addon">%</span>
        </div>
      </div>
    </div>
    <div class="checkbox">
      <i class="icofont-truck-loaded icofont-2x padding-10"></i>
      <label class="form-check-label">
        Necesita transporte especial?
        <input type="checkbox" v-model="specialTransport" class="form-check-input" />
      </label>
    </div>
    <br>
      <div class="form-group">
        <label for="pImage">Imagen: </label>
        <div class="input-group">
          <button @click="onFileSelect" type="button" class="form-control-file" id="pImage">Seleccionar archivo</button>
        </div>
        <small id="emailHelp" class="form-text text-muted" v-if="imageName === null">Añada la imagen del producto</small>
        <p class="form-text text-muted" v-if="imageName !== null">{{ this.imageName }}</p>
        <p id="emailHelp" class="error" v-if="errorImagen">
          El fichero es demasiado grande. Intente otro, por favor
        </p>
      </div>
      <br>
    <button type="submit" class="submit">Guardar</button>
  </form>
</template>

<script>
import ProductService from '@/services/ProductService.js'
import * as filestack from 'filestack-js'
import Swal from 'sweetalert2'

export default {
  props: ['product'],
  data () {
    return {
      id: '',
      name: '',
      nameENG: '',
      description: '',
      descriptionENG: '',
      shortDesc: '',
      shortDescENG: '',
      image: '',
      imageName: '',
      price: '',
      discount: 0,
      specialTransport: false,
      dbProduct: '',
      maxLength: 50,
      errorNombre: false,
      errorDesc: false,
      errorPrecio: false,
      errorImagen: false
    }
  },
  beforeMount () {
    if (this.product) {
      this.fillData()
    }
  },
  methods: {
    checkform () {
      if (this.name && this.shortDesc && this.price) {
        this.errorNombre = false
        this.errorDesc = false
        this.errorPrecio = false
        const newPruduct = {
          id: this.id,
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
          .then(response => {
            console.log(response.data)
            Swal.fire({
              icon: 'success',
              title: this.$t('product.save'),
              showConfirmButton: false,
              timer: 1500
            })
            // const eventsBus = useEventsBus()
            // const emit = eventsBus.emit
            // emit('product-updated', true)
            this.$router.go(-1)
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
    fillData () {
      if (this.product.id) {
        this.$data.id = this.product.id
      }
      if (this.product.name) {
        this.$data.name = this.product.name
      }
      if (this.product.nameENG) {
        this.$data.nameENG = this.product.nameENG
      }
      if (this.product.description) {
        this.$data.description = this.product.description
      }
      if (this.product.descriptionENG) {
        this.$data.descriptionENG = this.product.descriptionENG
      }
      if (this.product.shortDesc) {
        this.$data.shortDesc = this.product.shortDesc
      }
      if (this.product.shortDescENG) {
        this.$data.shortDescENG = this.product.shortDescENG
      }
      if (this.product.image) {
        this.$data.image = this.product.image
      }
      if (this.product.discount != null) {
        this.$data.discount = this.product.discount
      }
      if (this.product.price != null) {
        this.$data.price = this.product.price
      }
      if (this.product.specialTransport != null) {
        this.$data.specialTransport = this.product.specialTransport
      }
    },

    onFileSelect () {
      const client = filestack.init(process.env.VUE_APP_FILESTACK_API_KEY)
      const options = {
        onFileSelected (file) {
          // Si el tamaño del archivo es más grande que 1 Mb, la descarga queda rechazada
          if (!file.size > 1048576) {
            this.errorImagen = true
          }
        },
        onUploadDone: res => {
          console.log(res.filesUploaded[0])
          this.image = res.filesUploaded[0].url
          this.imageName = res.filesUploaded[0].filename
        }
      }
      client.picker(options).open()
    }
  }
}
</script>