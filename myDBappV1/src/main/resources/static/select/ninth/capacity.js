$(document).on("click", "#capacityButton", function() {
    $.get('/select/ninthAddCapacity', function(html){
        jQuery('#addCapacity').html(html);
    });
});

$(document).on("click", "#removeCapacityButton", function() {
    $.get('/select/ninthRemoveCapacity', function(html){
        jQuery('#addCapacity').html(html);
    });
});