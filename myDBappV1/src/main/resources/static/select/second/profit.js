$(document).on("click", "#profitButton", function() {
    $.get('/select/secondAddProfit', function(html){
        jQuery('#addProfit').html(html);
    });
});

$(document).on("click", "#removeProfitButton", function() {
    $.get('/select/secondRemoveProfit', function(html){
        jQuery('#addProfit').html(html);
    });
});