import 'app/styles/users.styles'
import templateUrl from 'app/html/users.template'

const controller =
  class FtUsersController {
    constructor ($log, $state, $interval, appService, $http, httpservice, localStorageService) {
      'ngInject'
      this.localStorageService = localStorageService
      this.$http = $http
      this.$interval = $interval
      this.httpservice = httpservice
      this.service = appService
      this.$state = $state
      this.flights = []
      this.users = []
      this.userFollowers = []
      this.userFollowing = []
      this.followersArr = []
      this.followingArr = []
      this.USER = this.localStorageService.get('username')
      this.PASSWD = this.localStorageService.get('password')
      this.reverseSort = false
      this.orderByField = ''
      this.startInterval()
      this.loadFlights()
      $log.log('ft-users is a go')
    }

    startInterval () {
      this.$interval(
        ::this.loadFlights,
        1000
      )
    }

    loadFlights () {
      this.httpservice.getAllFlights()
      .then((response) => {
        if (response) {
          this.flights = (response)
        } else {
          console.log('failed get all flights')
          return false
        }
      }, (error) => {
        console.log(error)
      })
    }

    // allUsers () {
    //   this.followers()
    //   this.following()
    //   this.loadUsers()
    // }
    //
    // following () {
    //   this.httpservice.getUserFollowing(this.USER)
    //   .then((response) => {
    //     if (response) {
    //       this.userFollowing = (response)
    //       this.loadFollowing()
    //     } else {
    //       console.log('failed get user following')
    //       return false
    //     }
    //   }, (error) => {
    //     console.log(error)
    //   })
    // }
    //
    // loadFollowing () {
    //   for (let i = 0; i < this.userFollowing.length; i++) {
    //     this.followingArr.push(this.userFollowing[i].username)
    //   }
    // }
    //
    // isUserFollower (username) {
    //   if (this.followingArr.indexOf(username) < 0 || this.USER === username) {
    //     return false
    //   } else {
    //     return true
    //   }
    // }
    //
    // followers () {
    //   this.httpservice.getUserFollowers(this.USER)
    //   .then((response) => {
    //     if (response) {
    //       this.userFollowers = (response)
    //       this.loadFollowers()
    //     } else {
    //       console.log('failed get user followers')
    //       return false
    //     }
    //   }, (error) => {
    //     console.log(error)
    //   })
    // }
    //
    // loadFollowers () {
    //   for (let i = 0; i < this.userFollowers.length; i++) {
    //     this.followersArr.push(this.userFollowers[i].username)
    //   }
    // }
    //
    // isUserFollowing (username) {
    //   if (this.followersArr.indexOf(username) < 0) {
    //     return false
    //   } else {
    //     return true
    //   }
    // }
    //
    // loadUsers () {
    //   this.httpservice.getAllUsers()
    //   .then((response) => {
    //     if (response) {
    //       this.users = (response)
    //     } else {
    //       console.log('failed get all users')
    //       return false
    //     }
    //   }, (error) => {
    //     console.log(error)
    //   })
    // }
    //
    // saveState (key, val) {
    //   this.localStorageService.set(key, val)
    // }
    //
    // goProfile (author) {
    //   this.saveState('author', author)
    //   this.$state.transitionTo('profile')
    // }
    //
    // makeParms (userFollowed) {
    //   const parms = {
    //     credentials: {
    //       username: this.USER,
    //       password: this.PASSWD
    //     },
    //     username: userFollowed
    //   }
    //   return parms
    // }
    //
    // follow (username) {
    //   this.httpservice.followUser(this.makeParms(username), username)
    //   .then(function successCallback (response) {
    //     console.log('follow success')
    //   }, function errorCallback (response) {
    //     console.log(response.status)
    //   })
    //   this.followingArr.push(username)
    //   this.following()
    // }
    //
    // unfollow (username) {
    //   this.httpservice.unFollowUser(this.makeParms(username), username)
    //   .then(function successCallback (response) {
    //     console.log('unfollow success')
    //   }, function errorCallback (response) {
    //     console.log(response.status)
    //   })
    //   this.followingArr = this.followingArr.filter(elem => elem !== username)
    //   // this.following()  // causes flicker and button doesn't change on first click
    // }
    logout () {
      this.localStorageService.remove('username')
      this.localStorageService.remove('password')
      this.localStorageService.remove('isAuthenticated')
      this.localStorageService.remove('author')
      this.$state.transitionTo('login')
    }

 }
export const ftUsers = {
  controller,
  templateUrl,
  controllerAs: 'users'
}
