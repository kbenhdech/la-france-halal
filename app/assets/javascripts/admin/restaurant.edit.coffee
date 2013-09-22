$ ->

  # Calcul du nombre de caractères dans le textarea
  charNumber = $('#description').val().length
  $('#charNumber').text(charNumber)
  $('#description').keyup ->
    $('#charNumber').text(this.value.length);