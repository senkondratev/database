$(document).on("click", "#profitButton", function() {
    $.get('/select/ninthAddProfit', function(html){
        jQuery('#addProfit').html(html);
    });
});

$(document).on("click", "#removeProfitButton", function() {
    $.get('/select/ninthRemoveProfit', function(html){
        jQuery('#addProfit').html(html);
    });
});