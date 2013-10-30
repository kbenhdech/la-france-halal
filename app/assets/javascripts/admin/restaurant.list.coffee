require ["/assets/javascripts/utils.js"], (utils) ->

  # Datatable sur la liste des restaurants
  utils.datatable("#restaurantTable")

  utils.confirmDialog(".confirm-delete")