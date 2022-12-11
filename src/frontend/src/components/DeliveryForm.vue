<template>
<div class="container">

  <form @submit.prevent="checkform">
    <div class="form-group">
      <label for="addr">{{ $t('delivery.addr') }}: *</label>
      <input
        v-model.trim="address"
        type="text"
        id="addr"
        class="form-control"
        :placeholder="$t('delivery.addrPlaceholder')"
      />
      <p class="error" v-if="errorAddress">{{ $t('delivery.addrError') }}</p>
    </div>

    <div class="form-group">
      <label for="rName">{{ $t('delivery.recipientName') }}:</label>
      <input
        v-model.trim="recipientName"
        type="text"
        id="rName"
        class="form-control"
      />
      <p class="error" v-if="errorName">{{ $t('delivery.nameError') }}</p>
    </div>

    <div class="form-group">
      <label for="rPhone">{{ $t('delivery.recipientPhone') }}:</label>
      <input
        v-model.trim="recipientPhone"
        type="tel"
        pattern="[0-9]{3}-[0-9]{3}-[0-9]{3}"
        id="rPhone"
        class="form-control"
      />
      <p>{{ $t('delivery.phoneFormat') }}</p>
      <p class="error" v-if="errorPhone">{{ $t('delivery.phoneError') }}</p>
    </div>

    <div class="form-group">
      <label for="note">{{ $t('delivery.note') }}: </label>
      <textarea
        v-model="note"
        id="note"
        class="form-control"
        rows="10"
        :placeholder="$t('delivery.notePlaceholder')"
      ></textarea>
    </div>

    <div class="form-group date" data-provide="datepicker">
      <label for="date">{{ $t('delivery.date') }}:</label>
      <div class="input-group">
        <datepicker
          v-model="deliveryDate"
          :lower-limit="availableFrom"
        />
      </div>
    </div>

    <div class="checkbox">
      <label class="form-check-label">
        {{ $t('delivery.surprise') }}
        <input
          type="checkbox"
          v-model="surprise"
          class="form-check-input"
      /></label>
      <br />
    </div>
    <button type="submit" v-if="saveVisible" @click="checkform" class="submit">{{ $t('delivery.save') }}</button>
  </form>
  </div>
</template>

<script>
import DeliveryFrontService from '@/services/DeliveryFrontService.js'
import Datepicker from 'vue3-datepicker'
import { ref } from 'vue'

export default {
  components: {
    Datepicker
  },
  props: ['id', 'specialTransport', 'delivery', 'toggle'],
  created (delivery) {
    if (this.delivery) {
      this.deliveryDate = delivery.deliveryDate
      this.address = delivery.address
      this.note = delivery.note
      this.recipientName = delivery.recipientName
      this.surprise = delivery.surprise
      this.recipientPhone = delivery.recipientPhone
      this.order_id = delivery.order_id
    }
  },
  data () {
    return {
      deliveryDate: ref(new Date().setDate(new Date().getDate() + 1)),
      address: '',
      note: '',
      recipientName: '',
      surprise: false,
      recipientPhone: '',
      errorAddress: false,
      errorName: false,
      errorPhone: false,
      availableFrom: ref(new Date()),
      saveVisible: true
    }
  },
  methods: {
    checkform () {
      if (this.address && this.recipientName && this.recipientPhone) {
        this.errorAddress = false
        this.errorName = false
        this.errorPhone = false
        const newDelivery = {
          date: this.deliveryDate,
          address: this.address,
          recipientName: this.recipientName,
          recipientPhone: this.recipientPhone,
          note: this.note,
          surprise: this.surprise,
          specialTransport: this.specialTransport,
          order_id: this.id
        }
        DeliveryFrontService.createNewDelivery(newDelivery)
        this.saveVisible = false
        this.toggle()
      } else {
        if (this.address === '') {
          this.errorAddress = true
        }
        if (this.recipientName === '') {
          this.errorName = true
        }
        if (this.recipientPhone === '') {
          this.errorPhone = true
        }
      }
    }
  }
}
</script>