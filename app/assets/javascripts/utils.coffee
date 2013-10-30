define [], () ->

  class Utils

    # Calcul du nombre de caractères dans un textarea
    @customTextarea = (id) ->
      $(id).after("<div class='row-fluid'><p class='help-block'><span id='charNumber'>0</span> caractères (Nombre maximal de caractères: 500)</p></div>")
      charNumber = $(id).val().length
      $('#charNumber').text(charNumber)
      $(id).keyup ->
        $('#charNumber').text(this.value.length)

    # DateTimePicker pour un input date
    @datetimepickerDay = (id) ->
      $(id).datetimepicker
        format : 'dd/mm/yyyy'
        # daysOfWeekDisabled: [0,6]
        startView: 2
        minView: 2
        maxView: 3
        language: "fr"
        weekStart: 1
        autoclose: true

    # Confirmation pour toutes les suppressions
    @confirmDialog = (id) ->
      $(this).on "submit", id, (event) ->
        currentForm = this
        event.preventDefault()
        bootbox.confirm "Confirmez-vous la suppression ?", (result) ->
          currentForm.submit() if result

    # Datatable par défaut
    @datatable = (id) ->
      $(id).dataTable
        aaSorting: [[1, "asc"]]
        bPaginate: true
        sPaginationType: "bootstrap"
        bLengthChange: false
        bFilter: true
        bSort: true
        bInfo: true
        bAutoWidth: false
        oLanguage:
          sUrl: "/assets/libs/dataTables/language/fr_FR.txt"