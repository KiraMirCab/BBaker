<template>
    <div class="wrapper">
      <div class="container">
        <div class="row">
            <div class="col-md-2"> {{ $t("BBorder.created") }}:</div>
            <div class="col-md"> {{ prettyDate(this.order.creationDate) }} </div>
        </div>
        <div class="row">
            <div class="col-md-2"> {{ $t("menu.products") }}: </div>
            <div class="col-md">
                <table>
                    <thead>
                        <tr>
                            <th></th>
                            <th>{{ $t("BBorder.name") }}</th>
                            <th>{{ $t("BBorder.price") }}</th>
                            <th>{{ $t("BBorder.quantity") }}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(item, i) in this.order.orderProductsDTO" :key="i">
                            <td><img class="small-image" :src="getImage(item.product_id)" alt="Image from the db" /></td>
                            <td>{{ getName(item.product_id) }}</td>
                            <td>{{ item.price }}€</td>
                            <td>{{ item.quantity }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2"> Total: </div>
            <div class="col-md"> {{ this.order.total }} €</div>
        </div>
        <div class="row">
            <div class="col-md-2"> {{ $t("BBorder.paid") }}:</div>
            <div v-if="(order.paidDate !== null)" class="col-md"> {{ prettyDate(this.order.paidDate) }} </div>
            <div v-if="(order.paidDate === null)" class="col-md"> {{ $t("product.notpaid") }} </div>

        </div>
    </div>
      <button @click="toggle()" class="btn btn-light">
        {{ $t("BBorder.back") }}
      </button>
    </div>
  </template>

<script>

export default {
  name: 'ExistingOrder',
  props: ['order', 'inventory', 'toggle'],
  data () {
    return {

    }
  },
  methods: {
    prettyDate (timestamp) {
      const newDate = new Date(timestamp)
      const options = { year: 'numeric', month: 'short', day: 'numeric' }
      if (this.$i18n.locale === 'es') {
        return newDate.toLocaleDateString('es-ES', options) + '  ' + newDate.toLocaleTimeString('es-ES', { hour: '2-digit', minute: '2-digit' })
      } else {
        return newDate.toLocaleDateString('en-EN', options) + '  ' + newDate.toLocaleTimeString('en-EN', { hour: '2-digit', minute: '2-digit' })
      }
    },
    getName (id) {
      const product = this.inventory.find(product => product.id === id)
      if (this.$i18n.locale === 'es') {
        return product.name
      } else {
        return product.nameENG
      }
    },
    getImage (id) {
      const product = this.inventory.find(product => product.id === id)
      return product.image
    }
  }
}
</script>