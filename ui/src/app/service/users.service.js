export class UsersService {
  constructor (defaultSettings) {
    'ngInject'
    Object.assign(this, defaultSettings)
  }

  startInterval () {
    console.log('start')
  }

  cancelInterval () {
    console.log('cancel')
  }

}
