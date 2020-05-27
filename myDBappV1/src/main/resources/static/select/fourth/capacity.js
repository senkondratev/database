$(document).on("click", "#capacityButton", function() {
    $.get('/select/fourthAddCapacity', function(html){
        jQuery('#addCapacity').html(html);
    });
});

$(document).on("click", "#removeCapacityButton", function() {
    $.get('/select/fourthRemoveCapacity', function(html){
        jQuery('#addCapacity').html(html);
    });
});