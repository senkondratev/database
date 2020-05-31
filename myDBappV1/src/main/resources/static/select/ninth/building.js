$(document).on("click", "#buildingButton", function() {
    $.get('/select/ninthAddBuilding', function(html){
        jQuery('#addBuilding').html(html);
    });
});

$(document).on("click", "#removeBuildingButton", function() {
    $.get('/select/ninthRemoveBuilding', function(html){
        jQuery('#addBuilding').html(html);
    });
});