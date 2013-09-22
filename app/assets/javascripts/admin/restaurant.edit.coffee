$ ->

  # Calcul du nombre de caractères dans le textarea
  customTextarea = (id) ->
      $(id).after("<div class='row-fluid'><p class='help-block'><span id='charNumber'>0</span> caractères (Nombre maximal de caractères: 500)</p></div>")
      charNumber = $(id).val().length
      $('#charNumber').text(charNumber)
      $(id).keyup ->
        $('#charNumber').text(this.value.length);

  customTextarea("#description")