export const usersConfig =
  ($stateProvider) => {
    'ngInject'
    $stateProvider.state({
      name: 'users',
      url: '/users',
      component: 'ftUsers'
      // onEnter: (UsersService) => UsersService.startInterval(),
      // onExit: (UsersService) => UsersService.cancelInterval()
    })
  }
