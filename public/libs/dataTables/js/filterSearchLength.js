$.fn.dataTableExt.oApi.fnFilterSearchLength = function (oSettings, iSize) {
    /*
    * Usage:       $('#example').dataTable().fnFilterSearchLength(); Default size : 2.
    */
    var _that = this,
        iSize = (typeof iSize == 'undefined') ? 2 : iSize;

    this.each(function (i) {
        $.fn.dataTableExt.iApiIndex = i;
        var $this = this;
        var anControl = $('table.dataTables input', _that.fnSettings().aanFeatures.f);

        anControl.unbind('keyup').bind('keyup', function(){
            if (anControl.val().length > iSize || anControl.val().length == 0) {
                $.fn.dataTableExt.iApiIndex = i;
                _that.fnFilter(anControl.val());
            }
        });
        return this;
    });
    return this;
}