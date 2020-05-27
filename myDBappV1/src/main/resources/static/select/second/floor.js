$(document).on("click", "#floorButton", function() {
    $.get('/select/secondAddFloor', function(html){
        jQuery('#addFloor').html(html);
    });
});

$(document).on("click", "#removeFloorButton", function() {
    $.get('/select/secondRemoveFloor', function(html){
        jQuery('#addFloor').html(html);
    });
});