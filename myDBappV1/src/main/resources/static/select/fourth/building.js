$(document).on("click", "#buildingButton", function() {
    $.get('/select/fourthAddBuilding', function(html){
        jQuery('#addBuilding').html(html);
    });
});

$(document).on("click", "#removeBuildingButton", function() {
    $.get('/select/fourthRemoveBuilding', function(html){
        jQuery('#addBuilding').html(html);
    });
});