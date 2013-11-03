require ["/assets/javascripts/utils.js"], (utils) ->

  utils.customTextarea("#description", 500)

  utils.datetimepickerDay("#lastVerification")

  utils.searchAddress("#city")

  utils.select2("#cookingSpecialities")

  #################
  # Map (début)
  #################

  # Options d'initialisation de la carte
  mapOptions =
    credentials: "AhorfKOW3s3kXvEiKWbRrQ7YAtPxflKiqDca1RZfN3Pfi_ISs_vO84BSW0Swxfjo" # clé bing map
    center: new Microsoft.Maps.Location(46.606111, 1.875278) # Localisation par défaut, au centre de la france
    showScalebar: true # Barre d'information distance en bas à droite
    mapTypeId: Microsoft.Maps.MapTypeId.road # Type de carte
    zoom: 15 # valeur du zoom de la carte
    disablePanning: true # Empêche de déplacer la carte
    disableZooming: true # Supprime le zoom par click
    showDashboard: false # Supprime la bar de navigation (zoom...)

  # Création de la carte
  map = new Microsoft.Maps.Map(
    document.getElementById("map")
    mapOptions
  )

  # Action déclenché après modification de l'adresse ou de la ville
  # Mais aussi au chargement de la page
  # appel une fonction de MAJ de la carte
  updateMap = () ->
    # Met à jour la carte
    geocodeCallback = (geocodeResult, userData) ->
      if geocodeResult != null && geocodeResult.results.length > 0
        location = geocodeResult.results[0].location
      if location?
        map.setView({ zoom: 15, center: location })
        # Ajout d'un pushpin
        map.entities.clear()
        pushpin = new Microsoft.Maps.Pushpin(location, null)
        map.entities.push pushpin
        $("#errorMapLocalisation").html("")
      else
        $("#errorMapLocalisation").html(Messages("error.map.localisation"))
    search = new Microsoft.Maps.Search.SearchManager(map)
    address = $("#address").val()
    cityId = $("#city").val()
    jsRoutes.controllers.api.AddressesApi.findById(cityId).ajax
      context: this
      type: "GET"
      dataType: "json"
      success: (city) ->
        newAdress = address + ", " + city.name
        search.geocode
          where: newAdress
          count: 10
          callback: geocodeCallback

  # Chargement du module de recherche, notamment par adresse -> point
  # Appel callback
  Microsoft.Maps.loadModule('Microsoft.Maps.Search', { callback: updateMap });

  #################
  # Map (fin)
  #################

  # Event : MAJ de la carte lorsque la ville est modifié
  $("#city").on("change", updateMap)

  # Event : MAJ de la carte lorsque l'adresse est modifié (après 2 secondes d'inactivités)
  utils.fireActionAfterTime("#address", 2000, updateMap)




