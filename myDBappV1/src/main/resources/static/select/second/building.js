$(document).on("click", "#buildingButton", function() {
    $.get('/select/secondAddBuilding', function(html){
        jQuery('#addBuilding').html(html);
    });
});

$(document).on("click", "#removeBuildingButton", function() {
    $.get('/select/secondRemoveBuilding', function(html){
        jQuery('#addBuilding').html(html);
    });
});