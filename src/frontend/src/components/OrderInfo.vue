<template>
    <div class="container">
        <div class="row">
            <div class="col-md-2"> {{ $t("BBorder.created") }}: </div>
            <div class="col-md"> {{ prettyDate(order.creationDate) }} </div>
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
                        <tr v-for="(item, i) in orderProducts" :key="i">
                            <td><img class="small-image" :src="item.product.image" alt="Image from the db" /></td>
                            <td v-if="this.$i18n.locale === 'es'">{{ item.product.name }}</td>
                            <td v-else>{{ item.product.nameENG }}</td>
                            <td>{{ item.price }}€</td>
                            <td>{{ item.quantity }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2"> Total: </div>
            <div class="col-md"> {{ order.total }} €</div>
        </div>
    </div>

</template>
<script>
export default {
  components: {},
  props: ['order', 'orderProducts', 'routerPath'],
  data () {
    return {
    }
  },
  methods: {
    prettyDate (timestamp) {
      const newDate = new Date(timestamp)
      const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }
      if (this.$i18n.locale === 'es') {
        return newDate.toLocaleDateString('es-ES', options) + '  ' + newDate.toLocaleTimeString('es-ES', { hour: '2-digit', minute: '2-digit' })
      } else {
        return newDate.toLocaleDateString('en-EN', options) + '  ' + newDate.toLocaleTimeString('en-EN', { hour: '2-digit', minute: '2-digit' })
      }
    }
  }
}
</script>