$(document).on("click", "#floorButton", function() {
    $.get('/select/ninthAddFloor', function(html){
        jQuery('#addFloor').html(html);
    });
});

$(document).on("click", "#removeFloorButton", function() {
    $.get('/select/ninthRemoveFloor', function(html){
        jQuery('#addFloor').html(html);
    });
});