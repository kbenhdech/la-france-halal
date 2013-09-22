/**
 * Tri par dates au format "dd/MM/yyyy HH:mm" ou "dd/MM/yyyy"
 * Préciser le sType : date-euro.
 * Ex :
 * aoColumnDefs:[
 *  {sType: "date-euro", aTargets: [2, 3 ]}
 *  ]
 *
 */

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
    "date-euro-pre": function ( a ) {
        if ($.trim(a) != '') {
            var frDatea = $.trim(a).split(' ');
            var frDatea2 = frDatea[0].split('/');
            var addTime = '0000';
            if (frDatea.length == 2) {
                var frTimea = frDatea[1].split(':');
                addTime =  frTimea[0] + frTimea[1];
            }
            var x = (frDatea2[2] + frDatea2[1] + frDatea2[0] + addTime) * 1;
        } else {
            var x = 100000000000; // = l'an 1000 ...
        }

        return x;
    },

    "date-euro-asc": function ( a, b ) {
        return a - b;
    },

    "date-euro-desc": function ( a, b ) {
        return b - a;
    }
} );