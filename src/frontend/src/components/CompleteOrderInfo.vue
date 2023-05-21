<template>
  <button @click="toggle()" class="btn btn-dark more-space">
    <i class="icofont-arrow-left icofont-2x"></i>
  </button>
  <h1 class="center">Order id: {{ orderDelivery.order.id}}</h1>

  <!-- Información de la entrega -->
  <div class="container">
    <div class="row padding-10">
        <div class="col-md-2 bold"> {{ $t("delivery.addr") }}:</div>
        <div class="col"> {{ orderDelivery.address }} </div>
    </div>
    <div class="row padding-10">
      <div class="col-md-2 bold"> {{ $t("delivery.date") }}:</div>
      <div class="col"> {{ prettyDate(orderDelivery.date) }} </div>
    </div>
    <div class="row padding-10">
      <div class="col-md-2 bold"> {{ $t("delivery.noteShort") }}:</div>
      <div class="col"> {{ orderDelivery.note }} </div>
    </div>
    <div class="row padding-10">
      <div v-if="orderDelivery.specialTransport" class="col d-flex align-items-center">
        <i class="icofont-truck-loaded icofont-3x padding-10"></i>
        {{ $t("delivery.transport") }}
      </div>
      <div v-if="orderDelivery.surprise" class="col d-flex align-items-center">
        <i class="icofont-gift icofont-3x padding-10"></i>
        {{ $t("delivery.gift") }}
      </div>
    </div>
  </div>

  <!-- Información sobre el producto -->
  <ExistingOrderVue
  :order="orderDelivery.order"
  :toggle="toggle"
  :past="false"
  />

  <!-- Historial de cambios -->
  <div class="container">
    <h3 class="center">Status change history</h3>
    <div v-for="item in statusChanges" :key="item.id" class="row padding-10">
      <div class="col-md-3"> {{ prettyDate(item.changeDate) }}</div>
      <div v-if="this.$i18n.locale === 'es'" class="col bold"> {{ item.status.name }} </div>
      <div v-else class="col bold"> {{ item.status.nameENG }} </div>
    </div>
    <br>
  </div>
  <br>
</template>

<script>
import DateTimeService from '@/services/DateTimeService'
import ExistingOrderVue from './ExistingOrder.vue'

export default {
  name: 'CompleteOrderInfo',
  props: ['orderDelivery', 'toggle', 'statusChanges'],
  components: {
    ExistingOrderVue
  },
  data () {
    return {
    }
  },
  methods: {
    prettyDate (date, locale) {
      return DateTimeService.prettyDate(date, locale)
    }
  }
}
</script>