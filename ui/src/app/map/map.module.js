import mapLocations from 'app/map/map.locations'
import { flightMap } from 'app/map/map.component.js'
import mapService from 'app/map/map.service'

export default
  angular
    .module('flight.map', [])
    .constant('locations', mapLocations)
    .component('flightMap', flightMap)
    .service('$map', mapService)
    .name
