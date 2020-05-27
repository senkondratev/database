$(document).on("click", "#floorButton", function() {
    $.get('/select/fourthAddFloor', function(html){
        jQuery('#addFloor').html(html);
    });
});

$(document).on("click", "#removeFloorButton", function() {
    $.get('/select/fourthRemoveFloor', function(html){
        jQuery('#addFloor').html(html);
    });
});