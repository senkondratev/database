$(document).on("click", "#dateButton", function() {
    $.get('/select/firstAddDates', function(html){
        jQuery('#addDate').html(html);
    });
});

$(document).on("click", "#removeDateButton", function() {
    $.get('/select/firstRemoveDates', function(html){
        jQuery('#addDate').html(html);
    });
});