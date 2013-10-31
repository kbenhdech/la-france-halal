define [], () ->

  class Utils

    # Calcul du nombre de caractères dans un textarea
    @customTextarea = (id, maxLength) ->
      $(id).after("<div class='row-fluid'><p class='help-block'><span id='charNumber'>0</span> " + Messages('js.customTextarea.maxLength', maxLength) + "</p></div>")
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
        bootbox.confirm Messages('confirm.delete.question'), (result) ->
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
          sUrl: Messages('js.datatable.conf.lang')

    @searchAddress = (id) ->
      $(id).select2
        placeholder: Messages('js.select2.ville.search')
        minimumInputLength: 3
        ajax:
          url: jsRoutes.controllers.api.AddressesApi.findByTerm().absoluteURL()
          dataType: "json"
          quietMillis: 100
          data: (term, page) ->
            q: term
          results: (data, page) ->
            results: data
        formatResult: (city) ->
          city.name
        formatSelection: (city) ->
          city.name
        initSelection: (element, callback) ->
          id = $(element).val()
          jsRoutes.controllers.api.AddressesApi.findById(id).ajax
            context: this
            type: "GET"
            dataType: "json"
            success: (city) ->
              callback(city)
    # Technique pour corriger un bug d'affichage avec bootstrap3 (box dans une box)
    # En complément de modifications css
    $(id).on "change", ->
      $container = $(this).prev(".select2-container")
      $container.height $container.children(".select2-choices").height()