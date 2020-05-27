$(document).on("click", "#capacityButton", function() {
    $.get('/select/secondAddCapacity', function(html){
        jQuery('#addCapacity').html(html);
    });
});

$(document).on("click", "#removeCapacityButton", function() {
    $.get('/select/secondRemoveCapacity', function(html){
        jQuery('#addCapacity').html(html);
    });
});