$ ->

  # Confirmation pour toutes les suppressions
  $(this).on "submit", ".confirm-delete", (event) ->
    currentForm = this
    event.preventDefault()
    bootbox.confirm "Confirmez-vous la suppression ?", (result) ->
      currentForm.submit() if result