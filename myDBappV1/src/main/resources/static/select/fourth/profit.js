$(document).on("click", "#profitButton", function() {
    $.get('/select/fourthAddProfit', function(html){
        jQuery('#addProfit').html(html);
    });
});

$(document).on("click", "#removeProfitButton", function() {
    $.get('/select/fourthRemoveProfit', function(html){
        jQuery('#addProfit').html(html);
    });
});