import mapLocations from 'app/map/map.locations'
import mapComponent from 'app/map/map.component.js'
import mapService from 'app/map/map.service'

export default
  angular
    .module('flight.map', ['ngMap'])
    .constant('locations', mapLocations)
    .component('flightMap', mapComponent)
    .service('$map', mapService)
    .name
