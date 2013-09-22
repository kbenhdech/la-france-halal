$ ->

  # Datatable sur la liste des restaurants
  $("#restaurantTable").dataTable
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