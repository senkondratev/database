$(document).on("change", "#buildingSelect", function () {
    var selected = $(this).val();
    $.get("/insert/roomValidate",
        {
            selectedBuildingId : selected
        }, function(html){
            jQuery('#floorSelect').html(html);
        });
})