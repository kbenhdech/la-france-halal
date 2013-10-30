require ["/assets/javascripts/utils.js"], (utils) ->

  utils.customTextarea("#description")

  utils.datetimepickerDay("#lastVerification")

  mapOptions =
    credentials: "AhorfKOW3s3kXvEiKWbRrQ7YAtPxflKiqDca1RZfN3Pfi_ISs_vO84BSW0Swxfjo"
    center: new Microsoft.Maps.Location(46.606111, 1.875278)
    showScalebar: true # Barre en bas à droite d'information distance
    mapTypeId: Microsoft.Maps.MapTypeId.road
    zoom: 5

  map = new Microsoft.Maps.Map(
    document.getElementById("map")
    mapOptions
  )

  displayInfobox = (e) ->
    pinInfobox.setOptions visible: true
  hideInfobox = (e) ->
    pinInfobox.setOptions visible: false

  # Retrieve the location of the map center
  #center = map.

  # Add a pin to the center of the map
  pin = new Microsoft.Maps.Pushpin(center,
    text: "1"
  )

  # Create the info box for the pushpin
  pinInfobox = new Microsoft.Maps.Infobox(new Microsoft.Maps.Location(46.606111, 1.875278),
    title: "My Pushpin"
    visible: false
  )

  # Add a handler for the pushpin click event.
  Microsoft.Maps.Events.addHandler pin, "click", displayInfobox

  # Hide the info box when the map is moved.
  Microsoft.Maps.Events.addHandler map, "viewchange", hideInfobox

  # Add the pushpin and info box to the map
  map.entities.push pin
  map.entities.push pinInfobox

  $("#ville").change ->
    zoomLevel = parseInt(document.getElementById('ville').value);
    map.setView({zoom:zoomLevel});

