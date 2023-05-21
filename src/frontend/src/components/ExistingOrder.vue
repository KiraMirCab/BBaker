<template>
    <div class="container">
      <div class="row padding-10">
        <div class="col-md-2"> {{ $t("BBorder.created") }}:</div>
        <div class="col"> {{ prettyDate(order.creationDate, this.$i18n.locale) }} </div>
      </div>
        <div class="row padding-10">
          <div class="col-md-2"> {{ $t("BBorder.paid") }}:</div>
          <div v-if="(order.paidDate !== null)" class="col-md"> {{ prettyDate(order.paidDate, this.$i18n.locale) }} </div>
          <div v-if="(order.paidDate === null)" class="col-md"> {{ $t("product.notpaid") }} </div>
        </div>
        <div class="row padding-10">
          <div class="col-md-2 bold"> Total: </div>
          <div class="col-md bold"> {{ order.total }} €</div>
        </div>
        <div class="row padding-10">
          <div class="col-md-2 bold"> Status: </div>
          <div class="col-md" v-if="this.$i18n.locale === 'es'"> {{ order.orderStatus.name }}</div>
          <div class="col-md" v-else> {{ order.orderStatus.nameENG }}</div>
        </div>
        <div class="row">
          <table class="table center table-striped">
            <thead>
              <tr>
                <th> </th>
                <th scope="col">{{ $t("BBorder.name") }}</th>
                <th scope="col">{{ $t("BBorder.price") }}</th>
                <th scope="col">{{ $t("BBorder.quantity") }}</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in order.orderProducts" :key="item.id">
                <td><img class="small-image" :src="item.product.image" alt="Image from the db" /></td>
                <td>{{ item.product.name }}</td>
                <td>{{ item.product.price }}€</td>
                <td>{{ item.quantity }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div v-if="past" class="centered">
        <button @click="toggle()" class="btn btn-dark more-space d-flex align-items-center">
          <i class="icofont-arrow-left icofont-2x"></i>
          {{ $t("BBorder.back") }}
        </button>
      </div>
</template>

<script>
import DateTimeService from '@/services/DateTimeService'

export default {
  name: 'ExistingOrder',
  props: ['order', 'toggle', 'past'],
  data () {
    return {
    }
  },
  methods: {
    prettyDate (timestamp, locale) {
      return DateTimeService.prettyDate(timestamp, locale)
    }
  }
}
</script>