let base64String = ''

class HelperService {
  encodeImage64 (event) {
    const file = event.target.files[0]
    console.log(file)
    // const reader = new FileReader()
    // reader.onload = function () {
    //   base64String = reader.result.replace('data:', '').replace(/^.+,/, '')
    //   //      const imageBase64Stringsep = base64String
    // }
    // reader.readAsDataURL(file)
    // alert(base64String)
    // return base64String
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = function () {
      // console.log(reader.result.replace('data:', '').replace(/^.+,/, ''))// base64encoded string
      base64String = reader.result
      console.log('inside ONLOAD before return' + base64String)
      return base64String
    }
    reader.onerror = function (error) {
      console.log('Error: ', error)
    }
  }
}

export default new HelperService()