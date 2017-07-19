import 'app/styles/login.styles'
import templateUrl from 'app/html/login.template'

const controller = class FtLoginController {
  constructor ($log, $state, $location, $http, ftGameSettings, $window, $rootScope, appService, localStorageService) {
    'ngInject'
    this.localStorageService = localStorageService
    this.service = appService
    this.settings = ftGameSettings
    this.$location = $location
    this.$window = $window
    this.$state = $state
    this.$http = $http
    $rootScope.$on(this.$location.$routeChangeStart, this.checkAuth())
    $log.log('ft-login is a go')
  }
  checkAuth () {
    let isauthenticated = this.localStorageService.get('isAuthenticated')
    if(isauthenticated) {
      this.$state.transitionTo('home')
    }
  }
  saveState (key, val) {
    this.localStorageService.set(key, val)
  }
  loginSubmit () {
    let user = this.username
    let pass = this.password
  //  alert(user +' '+pass)
    this.$http({
      method: 'POST',
      url: 'http://localhost:8000/users/users/validate/user',
      data: {credentials: { password: this.password, username: this.username }}
    }).then(this.successCallback = (response) => {
      if (user === this.username)
      this.saveState('username', user)
      this.saveState('password', pass)
      this.saveState('isAuthenticated', true)
      this.$state.transitionTo('home')
    }, this.errorCallback = (response) => {
      this.error = 'Username or password are incorrect, please try again.'
    })
  }
  registerSubmit () {
      this.$http({
        method: 'POST',
        url: 'http://localhost:8000/users',
         params: { firstname: this.firstname, lastname: this.lastname, username: this.regusername, password: this.regpassword }
        // data:
        // {
        //   "user": {
        //     "username": this.regusername,this.regpassword
        //     "password": this.regpassword,
        //     "firstname": this.firstname,
        //     "lastname": this.lastname
        //     }
        //   }
        })
     .then((response) => {
      alert(JSON.stringify(response.data) + '3')
       if (response.status === 201) {
         this.saveState('username', this.regusername)
         this.saveState('password', this.regpassword)
         this.saveState('isAuthenticated', true)
         this.$state.transitionTo('home')
         alert('User Created!' + ' ' + '201')
       }
     })
  }

  checkUser () {
    this.$http({
      method: 'GET',
      url: 'http://localhost:8080/validate/username/available/@' + this.regusername + ''
    }).then(this.successCallback = (response) => {
      return true
    }, this.errorCallback = (response) => {
      this.error = 'Username or password are incorrect, please try again.'
    })
  }
}
export const ftLogin = {
  controller,
  templateUrl,
  controllerAs: 'login'
}
