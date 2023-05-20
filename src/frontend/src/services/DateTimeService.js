export default {
  prettyDate (timestamp, locale) {
    const newDate = new Date(timestamp)
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }
    if (timestamp == null) {
      return ''
    } else {
      if (locale === 'es') {
        return newDate.toLocaleDateString('es-ES', options) + '  ' + newDate.toLocaleTimeString('es-ES', { hour: '2-digit', minute: '2-digit' })
      } else {
        return newDate.toLocaleDateString('en-EN', options) + '  ' + newDate.toLocaleTimeString('en-EN', { hour: '2-digit', minute: '2-digit' })
      }
    }
  },
  prettyDateShort (timestamp, locale) {
    const newDate = new Date(timestamp)
    const options = { year: 'numeric', month: 'numeric', day: 'numeric' }
    if (timestamp == null) {
      return ''
    } else {
      if (locale === 'es') {
        return newDate.toLocaleDateString('es-ES', options) + '  ' + newDate.toLocaleTimeString('es-ES', { hour: '2-digit', minute: '2-digit' })
      } else {
        return newDate.toLocaleDateString('en-EN', options) + '  ' + newDate.toLocaleTimeString('en-EN', { hour: '2-digit', minute: '2-digit' })
      }
    }
  },
  prettyDateFromDate (date, locale) {
    const newDate = new Date(date)
    const options = { year: 'numeric', month: 'numeric', day: 'numeric' }
    if (date == null) {
      return ''
    } else {
      if (locale === 'es') {
        return newDate.toLocaleDateString('es-ES', options)
      } else {
        return newDate.toLocaleDateString('en-EN', options)
      }
    }
  }
}
